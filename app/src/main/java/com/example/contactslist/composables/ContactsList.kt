package com.example.contactslist.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState,
        modifier = modifier
    ) {
        itemsIndexed(contacts) { index, contact ->
            ContactCard(
                fullName = contact.getFullName(),
                phoneNumber = contact.phoneNumber,
                gender = contact.gender,
                onClick = { onCardClick(contact) }
            )
            if (index < contacts.lastIndex) {
                Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.tertiaryContainer)
            }
        }
    }
}