package com.chat.chat.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chat.chat.adapter.UserAdapter
import com.chat.chat.database.DatabaseHelper
import com.chat.databinding.ActivityUserBinding
class UserActivity : AppCompatActivity(  ) {
    private lateinit var  binding: ActivityUserBinding
    private lateinit var adapter: UserAdapter
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val currentUsername = sharedPreferences.getString("current_userrname", "")

        // Periksa apakah currentUserUsername tidak kosong
        if (!currentUsername.isNullOrEmpty()) {
            // Jika tidak kosong, dapatkan daftar pengguna kecuali pengguna yang sedang login
            val currentUserList = databaseHelper.getAllUsers(currentUsername)
            adapter = UserAdapter(currentUserList, this)
            binding.usersRecyclerView.adapter = adapter
        } else {
            // Jika currentUserUsername kosong, tampilkan pesan kesalahan
            Toast.makeText(this, "Username pengguna saat ini tidak tersedia", Toast.LENGTH_SHORT).show()
        }

        Log.d("SharedPreferences", "Nilai currentUserUsername: $currentUsername")


        binding.imageBack.setOnClickListener {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        }


        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
    }
//    override fun onResume() {
//    super.onResume()
//    adapter.refreshData(databaseHelper.getAllUsers())
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun getData(){
//    list.clear()
//    list.addAll(databaseHelper.getAllUsers())
//    adapter.notifyDataSetChanged()
//    }
}