package com.example.tixmaster.ui.screens.eventdetail

import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EventScreenDetailAppBar(navigateToEventScreen:  () -> Unit) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackedClicked =  navigateToEventScreen)
        },
        title = {
            Text(text = "Detail")
        },

    )
}

@Composable
fun BackAction(onBackedClicked: () -> Unit) {
    IconButton(modifier = Modifier.width(35.dp), onClick = { onBackedClicked() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Icon",
        )

    }
}
