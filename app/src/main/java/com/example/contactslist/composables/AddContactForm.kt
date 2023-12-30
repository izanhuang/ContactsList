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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.contactslist.Gender
import com.example.contactslist.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactForm(
    sheetState: SheetState,
    scope: CoroutineScope,
    updateShowBottomSheet: (Boolean) -> Unit,
    addContact: (String, String, String, Gender) -> Unit,
    modifier: Modifier = Modifier
) {
    var firstName: String by remember { mutableStateOf("") }
    var lastName: String by remember { mutableStateOf("") }
    var phoneNumber: String by remember { mutableStateOf("") }
    var gender: Gender by remember { mutableStateOf(Gender.MALE) }

    val avatar =
        if (gender == Gender.MALE) R.drawable.round_avatar_male_24 else R.drawable.round_avatar_female_24

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
                    value = firstName,
                    onValueChange = { value -> firstName = value },
                    label = { Text("First name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { value -> lastName = value },
                    label = { Text("Last name") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = stringResource(
                    id = R.string.phone
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { value -> phoneNumber = value },
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
                    selected = gender == Gender.MALE,
                    onClick = { gender = Gender.MALE }
                )
                Text("Male")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = gender == Gender.FEMALE,
                    onClick = { gender = Gender.FEMALE }
                )
                Text(text = "Female")
            }
        }
        Button(
            onClick = {
                addContact(firstName, lastName, phoneNumber, gender)
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        updateShowBottomSheet(false)
                    }
                }
            }
        ) {
            Text("Add")
        }
    }
}