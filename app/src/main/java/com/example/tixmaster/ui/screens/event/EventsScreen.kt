package com.example.tixmaster.ui.screens.event

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tixmaster.R
import com.example.tixmaster.model.Event
import com.example.tixmaster.navigation.Screen
import com.example.tixmaster.util.convertDate
import com.example.tixmaster.util.convertTime


@Composable
fun EventScreen(
    navController: NavHostController,
    eventsViewModel: EventsViewModel = hiltViewModel()
) {
    val events by eventsViewModel.events.collectAsState()
    ListContent(events = events, navController )
}


@Composable
fun ListContent(events: List<Event>, navController: NavHostController ) {
    DisplayEventList(events = events, navController )
}

@Composable
fun DisplayEventList(events: List<Event>, navController: NavHostController ) {
    LazyColumn{
        items(items = events){ event ->
            EventItem(event = event) {
                navController.navigate("event_detail/${event.id}")
            }
        }
    }
}

@Composable
fun EventItem(event: Event, onClick: () -> Unit) {

    val eventDate = event.dates.start.localDate
    val eventTime = event.dates.start.localTime

    val date = eventDate.convertDate(LocalContext.current)!!
    val time = eventTime?.convertTime(LocalContext.current)

    val formattedTime =   if (event.dates.start.timeTBA) "TBA" else time

    val venue = event.embeddedEventDetail.venues.first().name
    val city = event.embeddedEventDetail.venues.first().city.name
    val stateCode = event.embeddedEventDetail.venues.first().state.stateCode

    val painter = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current)
        .data(event.images.first().url)
        .crossfade(durationMillis = 1000)
        .build(),
        placeholder = painterResource(R.drawable.ic_placeholder),
        error = painterResource(id = R.drawable.ic_placeholder)
    )

    Log.d("Event Screen", "$date $formattedTime")

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RectangleShape
    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                       onClick()
            },) {
            
            Image(
                modifier = Modifier
                    .height(75.dp)
                    .width(75.dp),
                painter = painter,
                contentDescription =" Event Image",
                contentScale = ContentScale.FillHeight
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(start = 8.dp), verticalArrangement = Arrangement.SpaceEvenly ) {

                Text(
                    text = event.name,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                )



                Text(
                    text = stringResource(id = R.string.event_date_time, date, formattedTime!!),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                )

                Text(
                    text = stringResource(id = R.string.event_venue_state_code, venue, city, stateCode),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                    maxLines = 1
                )
            }
            


        }
        
    }
}

@Composable
@Preview
fun EventScreenPreview() {
    //EventItem()
}