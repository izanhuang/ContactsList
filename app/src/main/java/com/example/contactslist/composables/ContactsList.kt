package com.example.contactslist.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contactslist.Contact

@Composable
fun ContactList(
    contacts: List<Contact>,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState,
        modifier = modifier
    ) {
        items(contacts) { contact ->
            ContactCard(
                fullName = contact.fullName,
                phoneNumber = contact.phoneNumber,
                gender = contact.gender
            )
        }
    }
}