package com.example.chatfiturrrr.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatfiturrrr.R
import com.example.chatfiturrrr.activity.SignInActivity
import com.example.chatfiturrrr.model.User

class UserAdapter (private var list: List<User>, context: Context) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textUsername: TextView = view.findViewById(R.id.textUsername)
        val textEmail: TextView = view.findViewById(R.id.textEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_container_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userList = list[position]
        holder.textUsername.text = userList.username
        holder.textEmail.text = userList.email
    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun refreshData(newList: List<User>){
//        list = newList
//        notifyDataSetChanged()
//    }
}