package com.trollonion.eunhasu

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.media.browse.MediaBrowser
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.internal.ConnectionCallbacks
import com.google.android.gms.common.api.internal.OnConnectionFailedListener
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import java.io.IOException
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {


    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startbutton.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
        infobutton.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }
        reportPolice.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:112")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        bugreport.setOnClickListener {
            var emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "trollonion03@email.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "")
            // emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses) // 여러개의 이메일 보낼 시
            startActivity(Intent.createChooser(emailIntent, ""))
        }
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
                    this@MainActivity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUSET_ACCESS_FINE_LOCATION)
            }
            noButton { }
        }.show()
    }

    inner class MyLocationCallback : LocationCallback() {
        var currentLocation : String = ""
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            val location = locationResult?.lastLocation


            location?.run {
                val latLng = LatLng(latitude, longitude)
                Log.d("CheckCurrentLocation", "현재 내 위치 값: $latitude, $longitude")

                var mGeocoder = Geocoder(applicationContext, Locale.KOREAN)
                var mResultList: List<Address>? = null
                try {
                    mResultList = mGeocoder.getFromLocation(
                        latitude!!, longitude!!, 1
                    )
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (mResultList != null) {
                    Log.d("CheckCurrentLocation", mResultList[0].getAddressLine(0))
                    currentLocation = mResultList[0].getAddressLine(0)
                    currentLocation = currentLocation.substring(11)
                    locText.text = currentLocation
                    Log.d("현재주소", "주소: $currentLocation")
                }
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
}




