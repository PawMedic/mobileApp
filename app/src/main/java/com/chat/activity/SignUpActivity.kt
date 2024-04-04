package com.chat.chat.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chat.chat.database.DatabaseHelper
import com.chat.databinding.ActivitySignUpBinding
import com.chat.data.User

class SignUpActivity : AppCompatActivity() {
    private  lateinit var  binding: ActivitySignUpBinding
    private lateinit var  databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.btnSignUp.setOnClickListener{
            val signupUsername = binding.etName.text.toString()
            val signupEmail = binding.etEmail.text.toString()
            val signupPassword = binding.etPassword.text.toString()
            val user = User(0, signupUsername, signupEmail, signupPassword)
            databaseHelper.insertUser(user)
            Toast.makeText(this, "SignUp Successfull", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.textSignIn.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        }
//    private fun signUpDatabase(username: String, email: String, password: String){
//        val insertRowId = databaseHelper.insertUser(username, email, password)
//        if (insertRowId != -1L){
//            Toast.makeText(this, "SignUp Succesfull", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, SignInActivity::class.java)
//            startActivity(intent)
//            finish()
//        }else{
//            Toast.makeText(this, "SignUp failed", Toast.LENGTH_SHORT).show()
//        }
//    }

}