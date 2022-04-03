package com.example.tixmaster.ui.screens.eventdetail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tixmaster.R
import com.example.tixmaster.model.Event
import com.example.tixmaster.navigation.Screen
import com.example.tixmaster.util.RequestState
import com.example.tixmaster.util.convertDate
import com.example.tixmaster.util.convertTime


@Composable
fun EventDetailScreen(navController: NavHostController, viewModel: EventDetailViewModel) {

    val eventDetail by viewModel.eventDetail.collectAsState()

    Scaffold(
        topBar = {
           EventScreenDetailAppBar {
               navController.navigate(Screen.EventScreen.route)
           }
        },
        content = {
            if (eventDetail is RequestState.Success) {
                EventDetailContent(event = eventDetail)
            }

        }
    )

}


@Composable
fun EventDetailContent(event: RequestState<Event>) {
    var eventImage = ""
    val context = LocalContext.current
    if (event is RequestState.Success) {

        val eventDetail = event.data

        eventDetail.images.forEach {
            if (it.ratio == "16_9" && it.height > 576)
                eventImage = it.url
        }

        val eventImagePainter = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current)
            .data(eventImage)
            .crossfade(durationMillis = 1000)
            .build(),
            placeholder = painterResource(R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_placeholder)
        )

        val seatMapImage = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current)
            .data(eventDetail.seatmap.staticURL)
            .crossfade(durationMillis = 1000)
            .build(),
            placeholder = painterResource(R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_placeholder)
        )

        val eventDate = eventDetail.dates.start.localDate
        val eventTime = eventDetail.dates.start.localTime


        val formattedTime =
            if (eventDetail.dates.start.timeTBA) "TBA" else eventTime?.convertTime(LocalContext.current)

        val formattedDate =
            if (eventDetail.dates.start.dateTBA) "TBA" else eventDate.convertDate(LocalContext.current)


        val venue = eventDetail.embeddedEventDetail.venues.first().name
        val city = eventDetail.embeddedEventDetail.venues.first().city.name
        val stateCode = eventDetail.embeddedEventDetail.venues.first().state.stateCode

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                    contentAlignment = Alignment.BottomCenter) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = eventImagePainter,
                        contentDescription = "Event Image",
                        contentScale = ContentScale.FillBounds
                    )
                }

                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp),
                    text = eventDetail.name ,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    text = stringResource(
                        id = R.string.event_classification,
                        eventDetail.classifications.first().segment.name,
                        eventDetail.classifications.first().genre.name,
                    ) ,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    text = eventDetail.classifications.first().subGenre.name ,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                )

                Row(modifier = Modifier
                    .padding(start = 8.dp, top = 16.dp)
                    .height(75.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_date),
                        contentDescription = "date icon"
                    )
                    Column(
                        Modifier
                            .padding(start = 16.dp)
                            .height(75.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = stringResource(id = R.string.event_date, formattedDate!!),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                        )

                        Text(
                            text = stringResource(id = R.string.event_venue_state_code, venue, city, stateCode) ,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                        )

                        Text(
                            text = formattedTime!! ,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                        )

                    }

                }

                Row(modifier = Modifier
                    .padding(start = 8.dp, top = 16.dp)
                    .height(45.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_price),
                        contentDescription = "date icon"
                    )
                    Column(
                        Modifier
                            .padding(start = 16.dp)
                            .height(45.dp), verticalArrangement = Arrangement.Top) {
                        Text(
                            text = "Price Range" ,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                        )

                        Text(
                            modifier = Modifier.padding(top = 6.dp),
                            text = stringResource(
                                id = R.string.event_price_range,
                                eventDetail.priceRanges?.first()?.min?: "NA",
                                eventDetail.priceRanges?.first()?.max?: "NA",

                                ),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                        )
                    }
                }

                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    text = "Seat Map"
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                    painter = seatMapImage,
                    contentDescription = "seat map",
                    contentScale = ContentScale.Inside
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    text = stringResource(
                        id = R.string.event_promoter,
                        eventDetail.promoter?.name?: "Not Available"
                    )
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
                    onClick = {
                        val browserIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(eventDetail.url)
                        )
                        startActivity(context, browserIntent, null)
                    }
                ) {
                    Text("Buy Tickets")
                }

            }
        }

    }
}





@Composable
@Preview
fun EventDetailScreenPreview() {

}