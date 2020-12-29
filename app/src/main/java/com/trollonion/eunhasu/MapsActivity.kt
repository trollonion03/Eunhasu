package com.trollonion.eunhasu

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast.makeText
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
import com.google.android.material.snackbar.Snackbar
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
        val s1 = LatLng(36.3714638, 127.4293431)
        val s2 = LatLng(36.3634669, 127.4418401)
        val s3 = LatLng(36.4487929, 127.4120375)
        val s4 = LatLng(36.3583834, 127.4188391)
        val s5 = LatLng(36.3773967, 127.4213867)
        val s6 = LatLng(36.346834, 127.4491915)
        val s7 = LatLng(36.3217517, 127.4407625)
        val s8 = LatLng(36.331075, 127.433988)
        val s9 = LatLng(36.2822129, 127.4673648)
        val s10 = LatLng(36.3519479, 127.4369953)
        val s11 = LatLng(36.3098333, 127.44885)
        val s12 = LatLng(36.3306026, 127.4742987)
        val s13 = LatLng(36.3526737, 127.3683)
        val s14 = LatLng(36.4400728, 127.3838011)
        val s15 = LatLng(36.375872, 127.3231455)
        val s16 = LatLng(36.358538, 127.3802371)
        val s17 = LatLng(36.3854154, 127.3780625)
        val s18 = LatLng(36.3489572, 127.3779265)
        val s19 = LatLng(36.3601523, 127.3668721)
        val s20 = LatLng(36.3581571, 127.3507125)
        val s21 = LatLng(36.299533, 127.316238)
        val s22 = LatLng(36.3599552, 127.3946458)
        val s23 = LatLng(36.304983, 127.353813)
        val s24 = LatLng(36.2991583, 127.3288625)
        val s25 = LatLng(36.3337654, 127.3811375)
        val s26 = LatLng(36.3087573, 127.3729718)
        val s27 = LatLng(36.310242, 127.373988)
        val s28 = LatLng(36.3787502, 127.3253684)
        val s29 = LatLng(36.3177522, 127.4305957)
        val s30 = LatLng(36.3266891, 127.4225925)
        val s31 = LatLng(36.3225179, 127.4140155)
        val s32 = LatLng(36.331517, 127.415938)
        val s33 = LatLng(36.3129417, 127.39535)
        val s34 = LatLng(36.3390737, 127.4125125)
        val s35 = LatLng(36.3546754, 127.3859742)

        mMap.addMarker(MarkerOptions().position(s1).title("대전대덕경찰서"))
        mMap.addMarker(MarkerOptions().position(s2).title("송촌지구대"))
        mMap.addMarker(MarkerOptions().position(s3).title("신탄진지구대"))
        mMap.addMarker(MarkerOptions().position(s4).title("중리지구대"))
        mMap.addMarker(MarkerOptions().position(s5).title("회덕파출소"))
        mMap.addMarker(MarkerOptions().position(s6).title("가양지구대"))
        mMap.addMarker(MarkerOptions().position(s7).title("대전동부경찰서"))
        mMap.addMarker(MarkerOptions().position(s8).title("대전역지구대"))
        mMap.addMarker(MarkerOptions().position(s9).title("산내파출소"))
        mMap.addMarker(MarkerOptions().position(s10).title("용전지구대"))
        mMap.addMarker(MarkerOptions().position(s11).title("천동파출소"))
        mMap.addMarker(MarkerOptions().position(s12).title("판암파출소"))
        mMap.addMarker(MarkerOptions().position(s13).title("갈마지구대"))
        mMap.addMarker(MarkerOptions().position(s14).title("구즉파출소"))
        mMap.addMarker(MarkerOptions().position(s15).title("노은지구대"))
        mMap.addMarker(MarkerOptions().position(s16).title("대전둔산경찰서"))
        mMap.addMarker(MarkerOptions().position(s17).title("도룡지구대"))
        mMap.addMarker(MarkerOptions().position(s18).title("둔산지구대"))
        mMap.addMarker(MarkerOptions().position(s19).title("월평지구대"))
        mMap.addMarker(MarkerOptions().position(s20).title("유성지구대"))
        mMap.addMarker(MarkerOptions().position(s21).title("진잠파출소"))
        mMap.addMarker(MarkerOptions().position(s22).title("청사지구대"))
        mMap.addMarker(MarkerOptions().position(s23).title("가수원파출소"))
        mMap.addMarker(MarkerOptions().position(s24).title("구봉지구대"))
        mMap.addMarker(MarkerOptions().position(s25).title("내동지구대"))
        mMap.addMarker(MarkerOptions().position(s26).title("대전서부경찰서"))
        mMap.addMarker(MarkerOptions().position(s27).title("도마지구대"))
        mMap.addMarker(MarkerOptions().position(s28).title("대전유성경찰서"))
        mMap.addMarker(MarkerOptions().position(s29).title("남대전지구대"))
        mMap.addMarker(MarkerOptions().position(s30).title("대전중부경찰서"))
        mMap.addMarker(MarkerOptions().position(s31).title("서대전지구대"))
        mMap.addMarker(MarkerOptions().position(s32).title("선화파출소"))
        mMap.addMarker(MarkerOptions().position(s33).title("유등지구대"))
        mMap.addMarker(MarkerOptions().position(s34).title("중촌파출소"))
        mMap.addMarker(MarkerOptions().position(s35).title("대전청"))
    }
}