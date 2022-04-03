package com.example.tixmaster.data.repository

import com.example.tixmaster.data.remote.TicketMasterApi
import com.example.tixmaster.model.Event
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(private val tixMasterApi: TicketMasterApi) {


    suspend fun getEvents() : Flow<List<Event>>{
        return flow {
            emit(tixMasterApi.getEvents().embedded.events)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getEventDetail(eventId: String) : Flow<Event>{
        return flow {
            emit(tixMasterApi.getEventDetail(eventId = eventId))
        }.flowOn(Dispatchers.IO)
    }
}