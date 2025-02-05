package com.jithus.myapplication

import androidx.lifecycle.ViewModel

/**
 * The class designed for the LoginViewModel
 */
class LoginViewModel : ViewModel() {
    fun getAuth(username:String, password:String) : String {
        return Repository().getAuth(username, password)
    }
}