package com.example.tixmaster.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: String,

    val name: String,

    val url: String,

    val images: List<Image>,

    val dates: Dates,

    val classifications: List<Classification>,

    val promoter: Promoter? = null,

    val priceRanges: List<PriceRange>? = null,

    val seatmap: SeatMap,

    @SerialName("_embedded")
    val embeddedEventDetail: EmbeddedEventDetail,


)

@Serializable
data class EmbeddedEventDetail(
    val venues: List<Venue>,

)

@Serializable
data class PriceRange (
    val type: String,
    val currency: String,
    val min: Double,
    val max: Double
)

@Serializable
data class Promoter (
    val id: String,
    val name: String,
    val description: String
)

@Serializable
data class Classification(
    val primary: Boolean,
    val segment: Genre,
    val genre: Genre,
    val subGenre: Genre,
    val type: Genre? = null,
    val subType: Genre? = null,
    val family: Boolean
)

@Serializable
data class Genre (
    val id: String,
    val name: String
)

@Serializable
data class Dates(
    val start: Start,
    val timezone: String? = null,
    val status: Status,
    val spanMultipleDays: Boolean
)

@Serializable
data class Start (
    val localDate: String,
    val localTime: String? = null,
    val dateTime: String? = null,
    val dateTBD: Boolean,
    val dateTBA: Boolean,
    val timeTBA: Boolean,
    val noSpecificTime: Boolean
)

@Serializable
data class Status (
    val code: String
)


@Serializable
data class Image(
    val ratio: String? = null,
    val url: String,
    val width: Long,
    val height: Long,
    val fallback: Boolean,
    val attribution: String? = null
)
@Serializable
data class SeatMap (
    @SerialName("staticUrl")
    val staticURL: String
)
