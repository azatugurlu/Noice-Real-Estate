package com.azat.noicerealestate.screens.common

import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.azat.noicerealestate.R

@Composable
fun MyApplicationTopBar(
    title: String?,
    includeBackButton: Boolean,
    onBackButton: () -> Unit
) {
    TopAppBar(
        backgroundColor = colors.primary,
        title =  {
            Text(text = title ?: "", color = Color.White)
        },
        navigationIcon = if (includeBackButton) {
            {
                IconButton(onClick = { onBackButton() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                        tint = Color.White
                    )
                }
            }
        } else {
            null
        }
    )
}