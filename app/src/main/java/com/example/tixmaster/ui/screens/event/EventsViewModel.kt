package com.example.tixmaster.ui.screens.event

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tixmaster.data.repository.Repository
import com.example.tixmaster.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    fun getEvents() {
        viewModelScope.launch {
            repository.getEvents().collect{
                Log.d("EventsViewModel", it.toString())
                _events.value = it
            }
        }
    }

    init {
        Log.d("EventsViewModel", "EventViewModel called")
        getEvents()
    }

}