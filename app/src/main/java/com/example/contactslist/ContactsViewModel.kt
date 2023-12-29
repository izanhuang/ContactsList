package com.example.contactslist

import androidx.lifecycle.ViewModel
import com.example.contactslist.ui.theme.getContacts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ContactsViewModel() : ViewModel() {
    private val _contacts = MutableStateFlow(getContacts())

    val contacts: StateFlow<List<Contact>> = _contacts

    fun addContact(firstName: String, lastName: String, phoneNumber: String, gender: Gender) {
        _contacts.value = _contacts.value + Contact(firstName, lastName, phoneNumber, gender)
    }
}