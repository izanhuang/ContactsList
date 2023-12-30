package com.example.contactslist.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.contactslist.Gender
import com.example.contactslist.R

@Composable
fun ContactCard(
    fullName: String, phoneNumber: String, gender: Gender, modifier: Modifier = Modifier
) {
    val avatar =
        if (gender == Gender.FEMALE) R.drawable.round_avatar_female_24 else R.drawable.round_avatar_male_24

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(8.dp)
        ) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            ) {
                Icon(
                    painter = painterResource(avatar),
                    contentDescription = stringResource(R.string.avatar),
                    modifier = Modifier
                        .size(64.dp)
                        .padding(12.dp)
                )
            }
            Spacer(Modifier.size(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                IconAndText(
                    drawableId = R.drawable.outline_badge_24,
                    descriptionId = R.string.full_name,
                    text = fullName
                )
                IconAndText(
                    imageVector = Icons.Filled.Phone,
                    descriptionId = R.string.phone,
                    text = phoneNumber
                )
            }
        }
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.tertiaryContainer)
    }
}

