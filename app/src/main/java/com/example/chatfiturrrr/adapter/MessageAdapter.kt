package com.example.chatfiturrrr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatfiturrrr.R
import com.example.chatfiturrrr.database.MessageDatabaseHelper
//import com.example.chatfiturrrr.database.MessageDatabaseHelper
import com.example.chatfiturrrr.messagesss.Message

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 1){
            //inflate receive
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_container_received_message, parent, false)
            return ReceiveViewHolder(view)
        }else{
            //inflate send
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_container_sent_message, parent, false)
            return SentViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        if(holder.javaClass == SentViewHolder::class.java) {
            //do the stuff for sent view holder
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message
        }else{
            //do the stuff for receive view holder
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text = currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        val senderId = currentMessage.senderId

        // Misalkan Anda memiliki ID pengguna saat ini disimpan di SharedPreferences
        val currentUser = getCurrentUserIdFromPreferences(context) // Fungsi ini harus Anda implementasikan

        return if (senderId == currentUser) {
            ITEM_SENT
        } else {
            ITEM_RECEIVE
        }
    }

    fun getCurrentUserIdFromPreferences(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("current_userrname", null)
    }
    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.textMessageSend)

    }
    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val receiveMessage = itemView.findViewById<TextView>(R.id.textMessageReceive)
    }
}