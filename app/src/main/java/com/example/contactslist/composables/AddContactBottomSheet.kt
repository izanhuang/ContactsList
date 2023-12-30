package com.example.contactslist.composables

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactBottomSheet(
    showBottomSheet: Boolean,
    sheetState: SheetState,
    scope: CoroutineScope,
    updateShowBottomSheet: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { updateShowBottomSheet(false) },
            sheetState = sheetState,
            modifier = modifier
        ) {
            Text("New contact")
            Button(onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        updateShowBottomSheet(false)
                    }
                }
            }) {
                Text("Add")
            }
        }
    }
}