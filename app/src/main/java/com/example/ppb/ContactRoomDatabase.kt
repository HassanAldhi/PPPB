package com.example.ppb

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version= 1, exportSchema = false)
abstract class ContactRoomDatabase : RoomDatabase() {
    abstract fun contactDao() : ContactDao?
    companion object {
        @Volatile
        private var INSTANCE: ContactRoomDatabase? = null
        fun getDatabase(context : Context) : ContactRoomDatabase ? {
            if (INSTANCE == null){
                synchronized(ContactRoomDatabase::class.java){
                    INSTANCE = databaseBuilder(context.applicationContext,
                        ContactRoomDatabase::class.java,"contact_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}