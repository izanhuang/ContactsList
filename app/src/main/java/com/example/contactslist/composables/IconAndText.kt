package com.example.contactslist.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun IconAndText(
    imageVector: ImageVector,
    @StringRes descriptionId: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = imageVector, contentDescription = stringResource(id = descriptionId))
        Spacer(Modifier.size(8.dp))
        Text(text = text, style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun IconAndText(
    @DrawableRes drawableId: Int,
    @StringRes descriptionId: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = drawableId),
            contentDescription = stringResource(id = descriptionId)
        )
        Spacer(Modifier.size(8.dp))
        Text(text = text, style = MaterialTheme.typography.headlineSmall)
    }
}