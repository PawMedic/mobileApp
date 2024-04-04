package com.chat.chat.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chat.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private  lateinit var  binding: ActivityMainBinding
//    private lateinit var  databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil nama pengguna dari SharedPreferences dan menampilkannya di textName
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val currentUsername = sharedPreferences.getString("current_username", "")
        binding.textName.text = currentUsername

        binding.imageSignOut.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.fabNew.setOnClickListener{
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }

//    override fun onResume() {
//        super.onResume()
//        val sharedPref = applicationContext?.getSharedPreferences("chatfitur", Context.MODE_PRIVATE)
//        val username = sharedPref?.getString("user_name", "-")
//
//        binding.textName.text = username
//
//    }
}