package com.example.tixmaster.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tixmaster.ui.screens.event.EventScreen
import com.example.tixmaster.ui.screens.event.EventsViewModel
import com.example.tixmaster.ui.screens.eventdetail.EventDetailScreen
import com.example.tixmaster.ui.screens.eventdetail.EventDetailViewModel
import com.example.tixmaster.util.Constants.EVENT_DETAIL_ARGUMENT_KEY
import kotlinx.coroutines.delay

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.EventScreen.route
    ) {
        composable(route = Screen.EventScreen.route){
            EventScreen(navController = navController)
        }
        composable(route = Screen.EventDetailScreen.route, arguments = listOf(navArgument(EVENT_DETAIL_ARGUMENT_KEY){
            type = NavType.StringType
        })){ navBackStackEntry ->

            Log.d("Event Detail","Detail Screen Called")
            val eventId = navBackStackEntry.arguments!!.getString(EVENT_DETAIL_ARGUMENT_KEY)
            Log.d("Event Detail","Event ID $eventId")
            val eventDetailViewModel: EventDetailViewModel = hiltViewModel()
            LaunchedEffect(key1 = eventId, block = {
                eventDetailViewModel.getEventDetail(eventId!!)

            })

            val eventDetail by eventDetailViewModel.eventDetail.collectAsState()

            Log.d("Event Detail",eventDetail.toString())

            EventDetailScreen(navController = navController, viewModel = eventDetailViewModel)

        }
    }
}