package com.kmx3.compose.ui.navflow.startflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModel @Inject constructor() : ViewModel(), AuthScreenEvents {

    private val _state =
        MutableStateFlow(
            AuthScreenState(
                "",
                "",
                isError = false,
                canGoNext = false
            )
        )

    val state = _state.asStateFlow()

    private val _events = Channel<Events>()
    val events = _events.receiveAsFlow()

    override fun onLoginEntered(name: String) {
    }

    override fun onPassEntered(name: String) {
    }

    override fun onAuth() {
        viewModelScope.launch {
            _events.send(Events.Auth)
        }
    }

    sealed class Events {
        data object Auth : Events()
        data object Exit : Events()
    }
}