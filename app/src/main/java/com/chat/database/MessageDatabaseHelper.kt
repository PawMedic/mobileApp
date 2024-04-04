package com.chat.chat.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.chat.chat.messagesss.Message

class MessageDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MessageDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "messages"
        private const val COLUMN_ID = "id"
        private const val COLUMN_MESSAGE = "message"
        private const val COLUMN_SENDER_ID = "senderid"
        private const val COLUMN_RECEIVER_ID = "receiverid"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_MESSAGE TEXT, " +
                "$COLUMN_SENDER_ID INTEGER, " +
                "$COLUMN_RECEIVER_ID INTEGER)")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertMessage(message: String, senderId: String?, receiverId: String?): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MESSAGE, message)
            put(COLUMN_SENDER_ID, senderId)
            put(COLUMN_RECEIVER_ID, receiverId)
        }
        val newRowId = db.insert(TABLE_NAME, null, values)
        db.close()
        return newRowId
    }

    @SuppressLint("Range")
    fun getAllMessages(): List<Message> {
        val messageList = mutableListOf<Message>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        cursor.use {
            while (it.moveToNext()) {
                val message = Message(
                    it.getString(it.getColumnIndex(COLUMN_MESSAGE)),
                    it.getString(it.getColumnIndex(COLUMN_SENDER_ID)),
                    it.getString(it.getColumnIndex(COLUMN_RECEIVER_ID))
                )
                messageList.add(message)
            }
        }
        db.close()
        return messageList
    }
    // Add other CRUD operations as needed
}
