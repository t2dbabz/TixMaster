package com.example.tixmaster.model

import kotlinx.serialization.Serializable

@Serializable
data class Venue (
    val name: String,
    val type: String,
    val id: String,
    val test: Boolean,
    val url: String? = null,
    val locale: String,
    val images: List<Image>? = null,
    val postalCode: String,
    val timezone: String,
    val city: City,
    val state: State,
    val country: Country,
    val address: Address,
    val location: Location,
    val accessibleSeatingDetail: String? = null,
    val generalInfo: GeneralInfo? = null,
)

@Serializable
data class GeneralInfo (
    val generalRule: String? = null,
    val childRule: String? = null
)

@Serializable
data class City (
    val name: String
)

@Serializable
data class Country (
    val name: String,
    val countryCode: String
)

@Serializable
data class Address (
    val line1: String
)

@Serializable
data class State (
    val name: String,
    val stateCode: String
)

@Serializable
data class Location (
    val longitude: String,
    val latitude: String
)




