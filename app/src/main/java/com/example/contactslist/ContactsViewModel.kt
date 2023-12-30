package com.example.contactslist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ContactsViewModel() : ViewModel() {
    private val _contacts = MutableStateFlow(getContacts())

    val contacts: StateFlow<List<Contact>> = _contacts

    init {
        _contacts.value = sortAlphabetically(_contacts.value)
    }

    fun addContact(firstName: String, lastName: String, phoneNumber: String, gender: Gender) {
        val listWithNewContact = _contacts.value + Contact(firstName, lastName, phoneNumber, gender)
        _contacts.value = sortAlphabetically(listWithNewContact)
    }

    private fun sortAlphabetically(
        list: List<Contact>,
        direction: SortDirection = SortDirection.ASCENDING
    ): List<Contact> {
        if (direction == SortDirection.ASCENDING) {
            return list.sortedBy { contact -> contact.fullName }
        }

        return list.sortedByDescending { contact -> contact.fullName }
    }
}