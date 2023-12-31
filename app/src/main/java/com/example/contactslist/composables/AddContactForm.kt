package com.example.contactslist.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.contactslist.Contact
import com.example.contactslist.Gender
import com.example.contactslist.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactForm(
    newContact: Contact,
    updateNewContact: (Contact) -> Unit,
    isNewContactValidPhoneNumber: Boolean,
    sheetState: SheetState,
    scope: CoroutineScope,
    updateShowBottomSheet: (Boolean) -> Unit,
    addContact: () -> Unit,
    modifier: Modifier = Modifier
) {
    val avatar =
        if (newContact.gender == Gender.MALE) R.drawable.round_avatar_male_24 else R.drawable.round_avatar_female_24
    val canSubmit =
        newContact.firstName.isNotEmpty() && newContact.lastName.isNotEmpty() && isNewContactValidPhoneNumber && newContact.gender.javaClass == Gender::class.java

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(
                painter = painterResource(R.drawable.outline_badge_24),
                contentDescription = stringResource(
                    id = R.string.full_name
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = newContact.firstName,
                    onValueChange = { value ->
                        updateNewContact(newContact.copy(firstName = value.trimStart().trimEnd()))
                    },
                    label = { Text("First name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = newContact.lastName,
                    onValueChange = { value ->
                        updateNewContact(newContact.copy(lastName = value.trimStart().trimEnd()))
                    },
                    label = { Text("Last name") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(
                imageVector = Icons.Filled.Phone, contentDescription = stringResource(
                    id = R.string.phone
                ), modifier = Modifier.padding(top = 8.dp)
            )
            OutlinedTextField(
                value = newContact.phoneNumber,
                onValueChange = { value ->
                    updateNewContact(newContact.copy(phoneNumber = value.trimStart().trimEnd()))
                },
                label = { Text("Phone number") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(avatar),
                contentDescription = stringResource(R.string.avatar)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = newContact.gender == Gender.MALE,
                    onClick = { updateNewContact(newContact.copy(gender = Gender.MALE)) })
                Text("Male")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = newContact.gender == Gender.FEMALE,
                    onClick = { updateNewContact(newContact.copy(gender = Gender.FEMALE)) })
                Text(text = "Female")
            }
        }
        Button(
            onClick = {
                addContact()
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        updateShowBottomSheet(false)
                    }
                }
            }, enabled = canSubmit
        ) {
            Text("Add")
        }
    }
}