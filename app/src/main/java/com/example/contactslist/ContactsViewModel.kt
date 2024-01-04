package com.example.contactslist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.contactslist.database.AppDatabase
import com.example.contactslist.types.BottomSheetStateType
import com.example.contactslist.types.Gender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class ContactsViewModel(private val db: AppDatabase) : ViewModel() {
    private val _contactsScreenState: MutableStateFlow<ContactScreenState> = MutableStateFlow(
        ContactScreenState(
            contacts = listOf(),
            newContact = Contact(),
            isNewContactValid = false,
            contactToUpdate = Contact(),
            isContactToUpdateValid = false,
            contactsListScrollIndex = null,
            currentlyOpenSheetState = null,
        )
    )

    val contactsScreenState: StateFlow<ContactScreenState> = _contactsScreenState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val contactDao = db.contactDao()
            _contactsScreenState.value = _contactsScreenState.value.copy(
                contacts = contactDao.getAll()
            )
        }
    }

    fun consumeScroll() {
        _contactsScreenState.value = _contactsScreenState.value.copy(
            contactsListScrollIndex = null
        )
    }

    fun toggleAddContactBottomSheet(sheetType: BottomSheetStateType?) {
        _contactsScreenState.value = _contactsScreenState.value.copy(
            currentlyOpenSheetState = sheetType
        )
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val pattern = "^[0-9]-[0-9]{3}-[0-9]{3}-[0-9]{4}$"

        return Pattern.matches(pattern, phoneNumber)
    }

    private fun isValidContact(contact: Contact): Boolean {
        return contact.firstName.isNotEmpty() && contact.lastName.isNotEmpty() && isValidPhoneNumber(
            contact.phoneNumber
        ) && contact.gender.javaClass == Gender::class.java

    }

    fun clearNewContact() {
        _contactsScreenState.value = _contactsScreenState.value.copy(
            newContact = Contact()
        )
    }

    fun updateNewContact(newContact: Contact) {
        _contactsScreenState.value = _contactsScreenState.value.copy(
            newContact = newContact,
            isNewContactValid = isValidContact(newContact)
        )
    }

    private fun clearContactToUpdate() {
        _contactsScreenState.value = _contactsScreenState.value.copy(
            contactToUpdate = Contact()
        )
    }

    fun updateContactToUpdate(contactToUpdate: Contact) {
        _contactsScreenState.value = _contactsScreenState.value.copy(
            contactToUpdate = contactToUpdate,
            isContactToUpdateValid = isValidContact(contactToUpdate)
        )
    }

    fun addContact() {
        val newContactFirstName =
            _contactsScreenState.value.newContact.firstName.replaceFirstChar { char -> char.uppercase() }
        val newContactLastName =
            _contactsScreenState.value.newContact.lastName.replaceFirstChar { char -> char.uppercase() }

        viewModelScope.launch(Dispatchers.IO) {
            val contactsDao = db.contactDao()
            val newContactRowId = contactsDao.insertContacts(
                _contactsScreenState.value.newContact.copy(
                    firstName = newContactFirstName,
                    lastName = newContactLastName
                )
            )
            _contactsScreenState.value = _contactsScreenState.value.copy(
                contacts = contactsDao.getAll()
            )
            val addedContact = contactsDao.getContactById(newContactRowId)
            val indexOfAddedContact = _contactsScreenState.value.contacts.indexOf(addedContact)
            _contactsScreenState.value = _contactsScreenState.value.copy(
                contactsListScrollIndex = indexOfAddedContact
            )
            clearNewContact()
        }
    }

    fun editContact() {
        val contactToUpdateFirstName =
            _contactsScreenState.value.contactToUpdate.firstName.replaceFirstChar { char -> char.uppercase() }
        val contactToUpdateLastName =
            _contactsScreenState.value.contactToUpdate.lastName.replaceFirstChar { char -> char.uppercase() }

        viewModelScope.launch(Dispatchers.IO) {
            val contactsDao = db.contactDao()
            contactsDao.updateContacts(
                _contactsScreenState.value.contactToUpdate.copy(
                    firstName = contactToUpdateFirstName,
                    lastName = contactToUpdateLastName
                )
            )
            val contactToUpdateRowId = _contactsScreenState.value.contactToUpdate.id
            _contactsScreenState.value = _contactsScreenState.value.copy(
                contacts = contactsDao.getAll()
            )
            val contactToUpdate = contactToUpdateRowId?.let { contactsDao.getContactById(it) }
            val indexOfContactToUpdate = _contactsScreenState.value.contacts.indexOf(contactToUpdate)
            _contactsScreenState.value = _contactsScreenState.value.copy(
                contactsListScrollIndex = if (indexOfContactToUpdate != -1) indexOfContactToUpdate else 0
            )
            clearContactToUpdate()
        }
    }

    fun removeContact() {
        viewModelScope.launch {
            val contactDao = db.contactDao()
            contactDao.deleteContacts(_contactsScreenState.value.contactToUpdate)
            _contactsScreenState.value = _contactsScreenState.value.copy(
                contacts = contactDao.getAll()
            )
            clearContactToUpdate()
        }
    }
}

class ContactViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
            val db = Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "app-database")
                .createFromAsset("databases/contacts.db")
                .build()
            @Suppress("UNCHECKED_CAST")
            return ContactsViewModel(db) as T
        }
        throw IllegalStateException("Unable to construct view model")
    }
}