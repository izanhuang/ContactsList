package com.example.contactslist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contactslist.types.Gender

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "first_name") val firstName: String = "",
    @ColumnInfo(name = "last_name") val lastName: String = "",
    @ColumnInfo(name = "phone_number") val phoneNumber: String = "",
    @ColumnInfo(name = "gender") val gender: Gender = Gender.MALE,
) {
    fun getFullName(): String {
        return "$firstName $lastName"
    }
}