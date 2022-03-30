package com.example.tixmaster.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventsResponse(
    @SerialName("_embedded")
    val embedded: EmbeddedResult,
)

@Serializable
data class EmbeddedResult(

    val events: List<Event>
)
