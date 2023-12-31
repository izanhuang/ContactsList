package com.example.contactslist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ContactsViewModel() : ViewModel() {
    private val _contacts = MutableStateFlow(getContacts())
    private val _newContact = MutableStateFlow(Contact())
    private val _isAddContactBottomSheetOpened = MutableStateFlow(false)

    val contacts: StateFlow<List<Contact>> = _contacts
    val newContact: StateFlow<Contact> = _newContact

    val isAddContactBottomSheetOpened: StateFlow<Boolean> = _isAddContactBottomSheetOpened

    init {
        _contacts.value = sortAlphabetically(_contacts.value)
    }

    fun toggleAddContactBottomSheet(shouldOpen: Boolean) {
        _isAddContactBottomSheetOpened.value = shouldOpen
    }

    fun clearNewContact() {
        _newContact.value = Contact()
    }

    fun updateNewContact(newContact: Contact) {
        _newContact.value = newContact
    }

    fun addContact(): Int {
        val listWithNewContact = _contacts.value + _newContact.value
        _contacts.value = sortAlphabetically(listWithNewContact)

        return _contacts.value.indexOf(_newContact.value)
    }

    private fun sortAlphabetically(
        list: List<Contact>, direction: SortDirection = SortDirection.ASCENDING
    ): List<Contact> {
        if (direction == SortDirection.ASCENDING) {
            return list.sortedBy { contact -> contact.fullName }
        }

        return list.sortedByDescending { contact -> contact.fullName }
    }
}