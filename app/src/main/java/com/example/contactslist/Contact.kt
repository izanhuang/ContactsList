package com.example.contactslist

data class Contact(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val gender: Gender = Gender.MALE
) {
    val fullName = "$firstName $lastName"
}

enum class Gender {
    FEMALE, MALE
}