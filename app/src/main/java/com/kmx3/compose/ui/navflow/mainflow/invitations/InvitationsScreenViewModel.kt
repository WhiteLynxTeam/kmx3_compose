package com.kmx3.compose.ui.navflow.mainflow.invitations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmx3.compose.ui.navflow.mainflow.MainFlowNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvitationsScreenViewModel @Inject constructor() : ViewModel(), InvitationsScreenEvents {

    private val _state =
        MutableStateFlow(
            InvitationsScreenState(
                "",
//                MainFlowNavigation.Routes.ShowcaseScreen
            )
        )

    val state = _state.asStateFlow()

    private val _events = Channel<Events>()
    val events = _events.receiveAsFlow()

    override fun onRequestQuota() {
    }

    override fun onSelectBottomMenu(item: MainFlowNavigation.Routes) {
        viewModelScope.launch {
            _events.send(Events.SelectBottomMenu(item))
        }
    }

    sealed class Events {
        data class SelectBottomMenu(val item: MainFlowNavigation.Routes) : Events()
    }
}