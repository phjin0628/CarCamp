package com.example.myapplication


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Transformations.map
import com.example.myapplication.mapdata.CarMapJson
import com.example.myapplication.mapdata.Ddu
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_car_map.*
import kotlinx.android.synthetic.main.activity_car_map.view.*
import okio.Okio.sink
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CarMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_map)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        loadCar()
    }

    fun loadCar() {
        val retrofit = Retrofit.Builder()
                .baseUrl(CarMapOpen.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val CarOpenService = retrofit.create(CarOpenService::class.java)

        CarOpenService
                .getCar(CarMapOpen.DOMAIN)
                .enqueue(object : Callback<CarMapJson> {
                    override fun onResponse(call: Call<CarMapJson>, response: Response<CarMapJson>) {
                        ShowCar(response.body() as CarMapJson)
                    }
                    override fun onFailure(call: Call<CarMapJson>, t: Throwable) {
                        Toast.makeText(baseContext, "????????? ????????? ??? ??????", Toast.LENGTH_LONG).show()
                    }
                })
    }

    fun ShowCar(Cars:CarMapJson){
        val latLngBounds= LatLngBounds.Builder()

        for(car in Cars.ddu){

            val position=LatLng(car.latitude.toDouble(),car.longitude.toDouble()) //position??? ?????? ??????

            val marker=MarkerOptions() //MarkerOptions?????? ??????????????? title ?????? snippet ???????????? ?????? ?????? ??? ??????
                    .position(position)
                    .title(car.Name)

            //mMap.addMarker(marker)??? ????????? ????????? //val marker=MarkerOptions() ?????? ????????? ????????? ?????????
            latLngBounds.include(marker.position)

            val marker1: Marker = mMap.addMarker(marker) //
            marker1.tag =
                    "${car.description}/${car.toilet}/${car.sink}/${car.tap_water}" //${}????????? ??????????????? ???????????? ??????
        }



        val positionss=LatLng(37.20917443124407, 126.976336711454)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(positionss,10.0F)
        mMap.moveCamera(cameraUpdate) //(updated)



        mMap!!.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                card_view.visibility = View.VISIBLE
                var carmapname = findViewById<TextView>(R.id.name)
                var carmapdescription = findViewById<TextView>(R.id.description)
                var carmaptoilet = findViewById<TextView>(R.id.toilet)
                var carmapsink = findViewById<TextView>(R.id.sink)
                var carmaptap_water = findViewById<TextView>(R.id.tap_water)
                carmapname.text = marker.title

                val tempArray = (marker.tag as String).split("/") //?????? ??????????????? ?????? ?????? /??? ???????????? ????????? ?????? ???
                carmapdescription.text = tempArray[0]
                carmaptoilet.text = tempArray[1]
                carmapsink.text = tempArray[2]
                carmaptap_water.text = tempArray[3]

                return false//????????????
            }
        })
        mMap!!.setOnMapClickListener(object : GoogleMap.OnMapClickListener {
            override fun onMapClick(latLng: LatLng) {
                card_view.visibility = View.GONE
            }
        })


    }
}