package com.example.contactslist.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contactslist.Contact
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BasicBottomSheet(
    contact: Contact,
    updateContact: (Contact) -> Unit,
    isContactValid: Boolean,
    buttonText: String,
    sheetTitle: String,
    sheetState: SheetState,
    closeBottomSheet: () -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = closeBottomSheet,
        sheetState = sheetState,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)) {
            Row {
                Text(
                    sheetTitle,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = {
                        onButtonClick()
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                closeBottomSheet()
                            }
                        }
                    }, enabled = isContactValid
                ) {
                    Text(buttonText)
                }
            }
            BasicContactForm(
                contact,
                updateContact,
            )
        }
    }
}