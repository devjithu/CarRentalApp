package com.jithus.myapplication

import android.Manifest
import android.app.ActivityManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jithus.myapplication.databinding.ActivityMainBinding

/**
 * The Class loads the Home Activity for the user.
 * From here it starts the Speed monitoring service.
 */
class UserHomeActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private val LOCATION_REQUEST = 1340

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val LOCATION_PERMS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
// Check Location can be accessible, if so, start the service
        if(canAccessLocation()) {
            startMonitoringService()
        } else {
//            request permission if permission is not granted
            requestPermissions(LOCATION_PERMS, LOCATION_REQUEST)
        }


    }

    /**
     * Start service if it is not running
     */
    private fun startMonitoringService() {
        if(!foregroundServiceRunning()) {
            val serviceIntent = Intent(
                this,
                SpeedMonitoringService::class.java
            )
            startForegroundService(serviceIntent)
        }
    }

    /**
     * The method checks whether Location permission granted or not
     */
    private fun canAccessLocation(): Boolean {
        return hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    /**
     * The method checks the given permission is granted or not
     */
    private fun hasPermission(perm: String): Boolean {
        return PackageManager.PERMISSION_GRANTED == checkSelfPermission(perm)
    }

    /**
     * The method gets triggered when the permission granted or denied when it is calling for requestpermission
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
                if (requestCode == LOCATION_REQUEST) {
                    if (canAccessLocation()) {
                        startMonitoringService()
                    }
                }
    }

    /**
     * The function checks whether the foreground service is running or not and return if the service is already running
     */
    fun foregroundServiceRunning(): Boolean {
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (SpeedMonitoringService::class.java.getName() == service.service.className) {
                return true
            }
        }
        return false
    }
}