package com.jithus.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * The class should get triggered when the device gets restarted.
 * Here it needs to check whether the monitoring service was running or not by saving the status in preference value.
 * if it was running, application can start the service after restart
 */
class RestartMonitorReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        TODO("Not yet implemented")
    }
}