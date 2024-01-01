package com.example.contactslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactslist.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class ContactsViewModel(private val db: AppDatabase) : ViewModel() {
    private val _contacts: MutableStateFlow<List<Contact>> = MutableStateFlow(listOf())
    private val _newContact : MutableStateFlow<Contact> = MutableStateFlow(Contact())
    private val _isAddContactBottomSheetOpened: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isNewContactValidPhoneNumber: MutableStateFlow<Boolean> =
        MutableStateFlow(isValidPhoneNumber(_newContact.value.phoneNumber))
    private val _contactsListScrollIndex: MutableStateFlow<Int?> = MutableStateFlow(null)

    val contacts: StateFlow<List<Contact>> = _contacts
    val newContact: StateFlow<Contact> = _newContact
    val contactsListScrollIndex: StateFlow<Int?> = _contactsListScrollIndex

    val isAddContactBottomSheetOpened: StateFlow<Boolean> = _isAddContactBottomSheetOpened
    val isNewContactValidPhoneNumber: StateFlow<Boolean> = _isNewContactValidPhoneNumber

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val contactDao = db.contactDao()
            _contacts.value = contactDao.getAll()
        }
    }

    fun consumeScroll() {
        _contactsListScrollIndex.value = null
    }

    fun toggleAddContactBottomSheet(shouldOpen: Boolean) {
        _isAddContactBottomSheetOpened.value = shouldOpen
    }

    private fun clearNewContact() {
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

    fun addContact() {
        val newContactFirstName =
            _newContact.value.firstName.replaceFirstChar { char -> char.uppercase() }
        val newContactLastName =
            _newContact.value.lastName.replaceFirstChar { char -> char.uppercase() }

        viewModelScope.launch(Dispatchers.IO) {
            val contactsDao = db.contactDao()
            val newContactRowId = contactsDao.insertContacts(
                _newContact.value.copy(
                    firstName = newContactFirstName,
                    lastName = newContactLastName
                )
            )
            val addedContact = contactsDao.getContactById(newContactRowId)
            _contacts.value = contactsDao.getAll()
            _contactsListScrollIndex.value = _contacts.value.indexOf(addedContact)
            clearNewContact()
        }
    }
}