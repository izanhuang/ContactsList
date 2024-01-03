package com.example.contactslist

import androidx.compose.runtime.Immutable
import com.example.contactslist.types.BottomSheetStateType

@Immutable
data class ContactScreenState(
    val contacts: List<Contact>,
    val newContact: Contact,
    val isNewContactValid: Boolean,
    val contactToUpdate: Contact,
    val isContactToUpdateValid: Boolean,
    val contactsListScrollIndex: Int?,
    val currentlyOpenSheetState: BottomSheetStateType?,
)