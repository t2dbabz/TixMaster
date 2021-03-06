package com.example.tixmaster.navigation

sealed class Screen(val route: String) {
    object EventScreen: Screen("event")
    object EventDetailScreen: Screen("event_detail/{eventId}")

}
