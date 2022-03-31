package com.example.tixmaster.data.remote

import com.example.tixmaster.model.EventsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TicketMasterApi {

    @GET("events")
    suspend fun getEvents(
        @Query("apikey") apiKey: String = "9YLBnYBSc2FwAXzJyowZiBSNY9DfG4fS",
        @Query("page") page: Int = 0,
        @Query("size")size: Int = 20,
    ): EventsResponse
}