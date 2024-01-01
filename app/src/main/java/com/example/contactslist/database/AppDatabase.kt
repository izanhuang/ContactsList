package com.example.contactslist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactslist.Contact

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}