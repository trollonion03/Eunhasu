package com.trollonion.eunhasu

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationInit()
    }

    private fun locationInit() {
        fusedLocationProviderClient = FusedLocationProviderClient(this)

        locationCallback = MyLocationCallback()

        locationRequest = LocationRequest()

        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        locationRequest.interval = 10000

        locationRequest.fastestInterval = 5000
    }

    private fun showPermissionInfoDialog() {
        alert("현재 위치 정보를 얻으려면 위치 권한이 필요합니다", "권한이 필요한 이유") {
            yesButton {
                ActivityCompat.requestPermissions(
                    this@MapsActivity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUSET_ACCESS_FINE_LOCATION)
            }
            noButton { }
        }.show()
    }

    inner class MyLocationCallback : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            val location = locationResult?.lastLocation


            location?.run {
                val latLng = LatLng(latitude, longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
            }
        }
    }

    private val REQUSET_ACCESS_FINE_LOCATION = 1000

    override fun onResume() {
        super.onResume()
        permissionCheck(cancel = {
            showPermissionInfoDialog()
        }, ok = {
            addLocationListener()
        })

        addLocationListener()
    }
    @SuppressLint("MissingPermission")
    private fun addLocationListener() {
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    override fun onPause() {
        super.onPause()
        removeLocationListener()
    }

    private fun removeLocationListener() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    private fun permissionCheck(cancel: () -> Unit, ok: () -> Unit) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                cancel()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUSET_ACCESS_FINE_LOCATION)
            }
        } else {
            ok()
        }
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
        val s1 = LatLng(36.3018897, 127.3780576)
        val s2 = LatLng(36.3018897, 127.3780576)
        val s3 = LatLng(36.3082432, 127.3774794)
        val s4 = LatLng(36.3108662, 127.3777191)
        val s5 = LatLng(36.310282, 127.375769)
        val s6 = LatLng(36.310525, 127.3770489)
        val s7 = LatLng(36.3099366, 127.3750618)
        val s8 = LatLng(36.29915788, 127.3778791)
        val s9 = LatLng(36.2983569, 127.3787793)
        val s10 = LatLng(36.3067297, 127.376909)
        val s11 = LatLng(36.3084941, 127.3766868)
        val s12 = LatLng(36.299283, 127.3743365)
        val s13 = LatLng(36.2985489, 127.3806906)
        val s14 = LatLng(36.29799524, 127.3773357)
        val s15 = LatLng(36.296064, 127.3764603)
        val s16 = LatLng(36.29647161, 127.3792432)
        val s17 = LatLng(36.307501, 127.3775666)
        val s18 = LatLng(36.2972839, 127.3805904)
        val s19 = LatLng(36.238622, 127.317439)
        val s20 = LatLng(36.295853, 127.3792564)
        val s21 = LatLng(36.238622, 127.317439)
        val s22 = LatLng(36.2942297, 127.378361)
        val s23 = LatLng(36.3092992, 127.3774376)
        val s24 = LatLng(36.3028797, 127.3753808)
        val s25 = LatLng(36.307236, 127.378414)
        val s26 = LatLng(36.2976586, 127.3789808)
        val s27 = LatLng(36.2970285, 127.3780873)
        val s28 = LatLng(36.2988339, 127.376214)
        val s29 = LatLng(36.2988339, 127.3762303)
        val s30 = LatLng(36.2977305, 127.3778379)
        val s31 = LatLng(36.2962086, 127.3760167)
        val s32 = LatLng(36.310027, 127.374809)
        val s33 = LatLng(36.2955662, 127.376652)
        val s34 = LatLng(36.307362, 127.373677)
        val s35 = LatLng(36.307738, 127.373529)

        mMap.addMarker(MarkerOptions().position(s1).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s2).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s3).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s4).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s5).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s6).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s7).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s8).title("좌우양방향"))
        mMap.addMarker(MarkerOptions().position(s9).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s10).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s11).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s12).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s13).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s14).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s15).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s16).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s17).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s18).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s19).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s20).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s21).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s22).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s23).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s24).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s25).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s26).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s27).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s28).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s29).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s30).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s31).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s32).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s33).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s34).title("360도 전방면"))
        mMap.addMarker(MarkerOptions().position(s35).title("360도 전방면"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(s10))
    }
}