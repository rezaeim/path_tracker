package edu.ecu.cs.pirateplaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.ecu.cs.pirateplaces.databinding.ActivityPiratePlacesMapBinding
import kotlin.properties.ReadOnlyProperty

class PiratePlacesMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityPiratePlacesMapBinding
    private lateinit var place: PiratePlace

    private val viewModel: ViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPiratePlacesMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(place.latitude, place.longitude)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


        viewModel.coordinates.observe(this, Observer {
            // Clear previous markers:
           // mMap?.clear()

            if (place.hasLocation == true) {
                // Place all current markers:
              // it.forEach { latLng ->
                 val lat = place.latitude
                val lon = place.longitude
                val latLng = LatLng(lat, lon)

                // Add a marker in Sydney and move the camera
                //val sydney = LatLng(-34.0, 151.0)
                mMap.addMarker(MarkerOptions().position(latLng).title("Marker in Sydney"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))


                }

        })


    }
}
