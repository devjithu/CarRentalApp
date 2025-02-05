package com.jithus.myapplication

import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.IBinder
import android.provider.Settings
import kotlinx.coroutines.coroutineScope

/**
 * The class starts the monitoring service by accessing the location
 */
class SpeedMonitoringService : Service() {
    var context: Context = this
//    This value should get from backend after login. But for now, using it as constant
    val allowedLimit = 5

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0.1f,LocationMonitor())
        startForegroundService()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    /**
     * The method starts the foreground service
     */
    fun startForegroundService(){
        CarRentalLogger().log("startForegroundService")
        val CHANNELID = "CarRental"
        val channel = NotificationChannel(
            CHANNELID,
            CHANNELID,
            NotificationManager.IMPORTANCE_LOW
        )

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification: Notification.Builder = Notification.Builder(this, CHANNELID)
            .setContentText("Car Monitoring Service is running")
            .setContentTitle("Service enabled")
            .setSmallIcon(R.drawable.ic_lock_lock)

        startForeground(1001, notification.build())
    }

    /**
     * The class implements LocationListner and hepls the application to monitor the speed
     */
    inner class LocationMonitor : LocationListener {
        /**
         * The method gets called when location changes
         */
    override fun onLocationChanged(location: Location) {
        if(location.speed > allowedLimit) {
            sendSpeedLimitNotification(location.speed);
        }
    }
// When provider is disabled
    override fun onProviderDisabled(provider: String) {
        val i = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(i)
    }
}

    /**
     * The method should implement the firebase communication by triggering the url to send notification
     * to the car owner for exceeding the speed limit
     * @param speed : The current speed obtained from Location
     */
    private fun sendSpeedLimitNotification( speed: Float) {
        CarRentalLogger().log("Speed exceeded, current speed $speed")
//        launch(Dispatchers.IO) {
    }
}