package com.jithus.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jithus.myapplication.databinding.LoginBinding

/**
 * The Activity class provides the login view.
 * It is design to manage both Admin and normal users login and navigate to corresponding home screen.
 * But now it is implemented only for normal users and
 * the speedlimit which is suppose to be set by the admin is hardcoded for now.
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLogin.setOnClickListener {
            view ->
            val role = viewModel.getAuth(binding.editTextUserName.text.toString(), binding.editTextTextPassword.text.toString())
            if(role.equals("admin", true)) {
                loadAdminScreen()
            } else {
                loadUserScreen()
            }
        }


    }

    /**
     * Loading normal user screen
     */
    fun loadUserScreen() {
        startActivity(Intent(this, UserHomeActivity::class.java).apply {
//            putExtra("maxSpeed", value)
        })
    }
//    Since admin screen is not implemented for now, keeping this as empty
    fun loadAdminScreen(){

    }
}
