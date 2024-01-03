package com.example.contactslist.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contactslist.Contact

@Composable
fun ContactList(
    contacts: List<Contact>,
    listState: LazyListState,
    onCardClick: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        state = listState,
        modifier = modifier
    ) {
        itemsIndexed(contacts) { index, contact ->
            ContactCard(
                fullName = contact.getFullName(),
                phoneNumber = contact.phoneNumber,
                gender = contact.gender,
                hasBottomBorder = index < contacts.lastIndex,
                onClick = { onCardClick(contact) }
            )
        }
    }
}