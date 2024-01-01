package com.example.contactslist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.contactslist.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY first_name, last_name ASC")
    suspend fun getAll(): List<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contacts: Contact): Long

    @Update
    suspend fun updateContacts(vararg contacts: Contact)

    @Delete
    suspend fun deleteContacts(vararg contacts: Contact)

    @Query("SELECT * FROM contacts WHERE id = :id")
    suspend fun getContactById(id: Long): Contact
}