package com.example.contactslist.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contactslist.Contact
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactBottomSheet(
    newContact: Contact,
    updateNewContact: (Contact) -> Unit,
    sheetState: SheetState,
    scope: CoroutineScope,
    updateShowBottomSheet: (Boolean) -> Unit,
    addContact: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = { updateShowBottomSheet(false) },
        sheetState = sheetState,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)) {
            Text(
                "New contact",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            AddContactForm(
                newContact,
                updateNewContact,
                sheetState,
                scope,
                updateShowBottomSheet,
                addContact
            )
        }
    }
}