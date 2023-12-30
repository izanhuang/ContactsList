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

    fun addContact(newContact: Contact): Int {
        val listWithNewContact = _contacts.value + newContact
        _contacts.value = sortAlphabetically(listWithNewContact)

        return _contacts.value.indexOf(newContact)
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