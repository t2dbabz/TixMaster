package com.example.tixmaster.ui.screens.event

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.tixmaster.ui.screens.eventdetail.BackAction

@Composable
fun EventScreenAppBar() {
    TopAppBar(

        title = {
            Text(text = "Events")
        },
        )
}