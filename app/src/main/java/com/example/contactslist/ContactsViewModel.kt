package com.example.contactslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class ContactsViewModel() : ViewModel() {
    private val _contacts: MutableStateFlow<List<Contact>> = MutableStateFlow(listOf())
    private val _newContact = MutableStateFlow(Contact())
    private val _isAddContactBottomSheetOpened = MutableStateFlow(false)
    private val _isNewContactValidPhoneNumber =
        MutableStateFlow(isValidPhoneNumber(_newContact.value.phoneNumber))

    val contacts: StateFlow<List<Contact>> = _contacts
    val newContact: StateFlow<Contact> = _newContact

    val isAddContactBottomSheetOpened: StateFlow<Boolean> = _isAddContactBottomSheetOpened
    val isNewContactValidPhoneNumber: StateFlow<Boolean> = _isNewContactValidPhoneNumber

    init {
        viewModelScope.launch {
            _contacts.value = sortAlphabetically(getContacts())
        }
    }

    fun toggleAddContactBottomSheet(shouldOpen: Boolean) {
        _isAddContactBottomSheetOpened.value = shouldOpen
    }

    fun clearNewContact() {
        _newContact.value = Contact()
    }

    fun updateNewContact(newContact: Contact) {
        _newContact.value = newContact
        _isNewContactValidPhoneNumber.value = isValidPhoneNumber(newContact.phoneNumber)
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val pattern = "^[0-9]-[0-9]{3}-[0-9]{3}-[0-9]{4}$"

        return Pattern.matches(pattern, phoneNumber)
    }

    fun addContact(): Int {
        val newContactFirstName =
            _newContact.value.firstName.replaceFirstChar { char -> char.uppercase() }
        val newContactLastName =
            _newContact.value.lastName.replaceFirstChar { char -> char.uppercase() }

        _newContact.value =
            _newContact.value.copy(
                firstName = newContactFirstName,
                lastName = newContactLastName
            )
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