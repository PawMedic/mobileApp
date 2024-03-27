package com.example.chatfiturrrr.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatfiturrrr.R
import com.example.chatfiturrrr.database.DatabaseHelper
import com.example.chatfiturrrr.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)


        binding.btnSignIn.setOnClickListener{
            val signupUsername = binding.etName.text.toString()
            val signupEmail = binding.etEmail.text.toString()
            val signupPassword = binding.etPassword.text.toString()
            signInDatabase(signupUsername,signupEmail, signupPassword)
        }
        binding.tvCreateNewAccount.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signInDatabase(username: String, email: String, password: String){
        val userExist = databaseHelper.readUser(username, email, password)
        if (userExist){
            val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("current_userrname", username)
            // Anda juga bisa menyimpan informasi lain tentang pengguna yang saat ini login
            editor.apply()

            Toast.makeText(this, "Sign In Succesfull", Toast.LENGTH_SHORT).show()
            saveUsernameToSharedPreferences(username)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show()
        }
    }
    private fun saveUsernameToSharedPreferences(username: String) {
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("current_username", username)
        editor.apply()
    }
}