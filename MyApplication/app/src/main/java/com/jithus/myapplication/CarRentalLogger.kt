package com.jithus.myapplication

import android.util.Log

/**
 * The class helps for logging for development tasks
 */
class CarRentalLogger {
    fun log(msg:String) {
        Log.v("MyLogger", msg)
    }
}