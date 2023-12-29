package com.example.contactslist

data class Contact(val firstName: String, val lastName: String, val phoneNumber: String, val gender: Gender) {
    val fullName = "$firstName $lastName"
}

enum class Gender {
    Female, Male
}