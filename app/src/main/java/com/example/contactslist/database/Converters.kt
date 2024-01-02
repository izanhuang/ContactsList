package com.example.contactslist.database

import androidx.room.TypeConverter
import com.example.contactslist.Gender
class Converters {
    @TypeConverter
    fun toGender(value: String) = enumValueOf<Gender>(value)

    @TypeConverter
    fun fromGender(value: Gender) = value.name
}
