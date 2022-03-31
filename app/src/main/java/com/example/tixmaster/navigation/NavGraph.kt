package com.example.tixmaster.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tixmaster.ui.screens.EventScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.EventsScreen.route
    ) {
        composable(route = Screen.EventsScreen.route){
            EventScreen(navController = navController)
        }
        composable(route = Screen.EventDetailScreen.route){

        }
    }
}