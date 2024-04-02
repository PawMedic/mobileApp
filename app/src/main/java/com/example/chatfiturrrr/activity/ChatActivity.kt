package com.example.chatfiturrrr.activity

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatfiturrrr.R
import com.example.chatfiturrrr.adapter.MessageAdapter
import com.example.chatfiturrrr.database.MessageDatabaseHelper
import com.example.chatfiturrrr.databinding.ActivityChatBinding
import com.example.chatfiturrrr.messagesss.Message


class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var inputMessage: EditText
    private lateinit var layoutSend: FrameLayout
    private lateinit var messageAdapter: MessageAdapter
    private  lateinit var messageList: ArrayList<Message>
    private lateinit var messageDatabaseHelper: MessageDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        val user_id = intent.getStringExtra("user_id")

        val textName = findViewById<TextView>(R.id.textName)
        textName.text = username

        if (username != null) {
            supportActionBar?.title = username
        }
        messageDatabaseHelper = MessageDatabaseHelper(this)

        chatRecyclerView = binding.chatRecyclerView
        inputMessage = binding.inputMessage
        layoutSend = binding.layoutSend

        messageList = ArrayList()
        messageAdapter = MessageAdapter(this,messageList)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter

        loadMessagesFromDatabase()

        layoutSend.setOnClickListener {
            val message = inputMessage.text.toString()
            if (message.isNotEmpty()) {
                sendMessageToDatabase(message)
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun loadMessagesFromDatabase() {
        // Ambil data pesan dari SQLite Database dan masukkan ke dalam messageList
        val messages = messageDatabaseHelper.getAllMessages()
        messageList.addAll(messages)
        messageAdapter.notifyDataSetChanged()
    }

    private fun sendMessageToDatabase(message: String) {
        // Simpan pesan baru ke SQLite Database
        val senderId = getCurrentUserIdFromPreferences(this)
        val receiverId = intent.getStringExtra("user_id")
        val messageId = messageDatabaseHelper.insertMessage(message, senderId, receiverId)

        // Jika pengiriman pesan berhasil, tambahkan pesan ke RecyclerView
        if (messageId != -1L) {
            val newMessage = Message(message, senderId, receiverId)
            messageList.add(newMessage)
            messageAdapter.notifyItemInserted(messageList.size - 1)
            chatRecyclerView.scrollToPosition(messageList.size - 1)
            inputMessage.text.clear()
        } else {
            Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show()
        }
    }
    fun getCurrentUserIdFromPreferences(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("current_userrname", null)
    }
}