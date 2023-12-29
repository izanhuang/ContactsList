package com.example.contactslist.ui.theme

import com.example.contactslist.Contact
import com.example.contactslist.Gender
import kotlin.random.Random

fun generateRandomPhoneNumber(): String {
    return "${Random.nextInt(0, 9)}-${Random.nextInt(100, 999)}-${
        Random.nextInt(
            100,
            999
        )
    }-${Random.nextInt(1000, 9999)}"
}

fun getContacts(): List<Contact> {
    return listOf(
        Contact("Tom", "Hanks", generateRandomPhoneNumber(), Gender.Male),
        Contact("Jennifer", "Lawrence", generateRandomPhoneNumber(), Gender.Female),
        Contact("Dwayne", "Johnson", generateRandomPhoneNumber(), Gender.Male),
        Contact("Emma", "Watson", generateRandomPhoneNumber(), Gender.Female),
        Contact("Leonardo", "DiCaprio", generateRandomPhoneNumber(), Gender.Male),
        Contact("Scarlett", "Johansson", generateRandomPhoneNumber(), Gender.Female),
        Contact("Chris", "Hemsworth", generateRandomPhoneNumber(), Gender.Male),
        Contact("Angelina", "Jolie", generateRandomPhoneNumber(), Gender.Female),
        Contact("Brad", "Pitt", generateRandomPhoneNumber(), Gender.Male),
        Contact("Gal", "Gadot", generateRandomPhoneNumber(), Gender.Female),
        Contact("Will", "Smith", generateRandomPhoneNumber(), Gender.Male),
        Contact("Margot", "Robbie", generateRandomPhoneNumber(), Gender.Female),
        Contact("Robert", "Downey Jr.", generateRandomPhoneNumber(), Gender.Male),
        Contact("Charlize", "Theron", generateRandomPhoneNumber(), Gender.Female),
        Contact("Chris", "Evans", generateRandomPhoneNumber(), Gender.Male),
        Contact("Natalie", "Portman", generateRandomPhoneNumber(), Gender.Female),
        Contact("Johnny", "Depp", generateRandomPhoneNumber(), Gender.Male),
        Contact("Cate", "Blanchett", generateRandomPhoneNumber(), Gender.Female),
        Contact("Hugh", "Jackman", generateRandomPhoneNumber(), Gender.Male),
        Contact("Meryl", "Streep", generateRandomPhoneNumber(), Gender.Female),
        Contact("Chris", "Pratt", generateRandomPhoneNumber(), Gender.Male),
        Contact("Anne", "Hathaway", generateRandomPhoneNumber(), Gender.Female),
        Contact("Matt", "Damon", generateRandomPhoneNumber(), Gender.Male),
        Contact("Julia", "Roberts", generateRandomPhoneNumber(), Gender.Female),
        Contact("Ryan", "Reynolds", generateRandomPhoneNumber(), Gender.Male),
        Contact("Emma", "Stone", generateRandomPhoneNumber(), Gender.Female),
        Contact("George", "Clooney", generateRandomPhoneNumber(), Gender.Male),
        Contact("Nicole", "Kidman", generateRandomPhoneNumber(), Gender.Female),
        Contact("Mark", "Wahlberg", generateRandomPhoneNumber(), Gender.Male),
        Contact("Halle", "Berry", generateRandomPhoneNumber(), Gender.Female)
    )
}