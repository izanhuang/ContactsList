package com.example.contactslist

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
        Contact("Tom", "Hanks", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Jennifer", "Lawrence", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Dwayne the Rock", "Johnson", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Emma", "Watson", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Leonardo", "DiCaprio", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Scarlett", "Johansson", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Chris", "Hemsworth", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Angelina", "Jolie", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Brad", "Pitt", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Gal", "Gadot", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Will", "Smith", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Margot", "Robbie", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Robert", "Downey Jr.", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Charlize", "Theron", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Chris", "Evans", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Natalie", "Portman", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Johnny", "Depp", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Cate", "Blanchett", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Hugh", "Jackman", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Meryl", "Streep", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Chris", "Pratt", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Anne", "Hathaway", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Matt", "Damon", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Julia", "Roberts", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Ryan", "Reynolds", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Emma", "Stone", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("George", "Clooney", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Nicole", "Kidman", generateRandomPhoneNumber(), Gender.FEMALE),
        Contact("Mark", "Wahlberg", generateRandomPhoneNumber(), Gender.MALE),
        Contact("Halle", "Berry", generateRandomPhoneNumber(), Gender.FEMALE)
    )
}