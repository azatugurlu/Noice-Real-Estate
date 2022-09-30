package com.azat.noicerealestate.screens.common.property

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.azat.domain.entity.Property

@Composable
fun PropertyHeader(property: Property) {
    Row(modifier = Modifier
        .padding(16.dp)) {
        Text(text = property.location, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.weight(1f))
        Text(text = property.buildingYear,
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Composable
fun PropertyTitle(property: Property) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Text(text = property.price,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.weight(1f))
        Text(text = property.size,
            style = MaterialTheme.typography.titleMedium
        )
    }
    Row(
        modifier = Modifier
            .padding(top = 4.dp, start = 16.dp)
            .fillMaxWidth()) {
        Text(text = property.roomDefinition,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}