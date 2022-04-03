package com.example.tixmaster.ui.screens.eventdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tixmaster.data.repository.Repository
import com.example.tixmaster.model.Event
import com.example.tixmaster.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _eventDetail = MutableStateFlow<RequestState<Event>>(RequestState.Loading)
    val eventDetail : StateFlow<RequestState<Event>> = _eventDetail


    fun getEventDetail(eventId: String ) {
        _eventDetail.value = RequestState.Loading

        try {
            viewModelScope.launch {

                repository.getEventDetail(eventId).collect {event ->
                    _eventDetail.value = RequestState.Success(event)
                }
            }
        } catch (e: Exception) {
            _eventDetail.value = RequestState.Error(e)
        }


    }
}