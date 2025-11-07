package com.kmx3.compose.ui.navflow.startflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmx3.compose.domain.DomainResult
import com.kmx3.compose.domain.models.User
import com.kmx3.compose.domain.usecases.AuthApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModel @Inject constructor(
    private val authUseCase: AuthApiUseCase
) : ViewModel(), AuthScreenEvents {

    private val _state =
        MutableStateFlow(
            AuthScreenState(
                "",
                "",
                isError = false,
                canGoNext = false,
                isLoading = false
            )
        )

    val state = _state.asStateFlow()

    private val _events = Channel<Events>()
    val events = _events.receiveAsFlow()

    override fun onLoginEntered(name: String) {
        _state.value = _state.value.copy(login = name)
    }

    override fun onPassEntered(name: String) {
        _state.value = _state.value.copy(pass = name)
    }

    override fun onAuth() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            println(" username = ${_state.value.login}, password = ${_state.value.pass}")

            val result = authUseCase(
                User(
                    username = _state.value.login,
                    password = _state.value.pass
                )
            )
            _state.value = _state.value.copy(isLoading = false)

            when (result) {
                is DomainResult.Success -> _events.send(Events.NavigateToMain)

                else -> _state.value = _state.value.copy(isError = true)
            }
        }
    }

    sealed class Events {
        data object Auth : Events()
        data object NavigateToMain : Events()
        data object Exit : Events()
    }
}
