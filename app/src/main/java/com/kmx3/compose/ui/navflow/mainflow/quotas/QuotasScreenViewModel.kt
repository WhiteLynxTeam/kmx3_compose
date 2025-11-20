package com.kmx3.compose.ui.navflow.mainflow.quotas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmx3.compose.domain.irepositories.IUserProfileRepository
import com.kmx3.compose.ui.navflow.mainflow.MainFlowNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotasScreenViewModel @Inject constructor(
    private val userProfileRepository: IUserProfileRepository
) : ViewModel(), QuotasScreenEvents {

    private val _state =
        MutableStateFlow(
            QuotasScreenState(
                "",
//                MainFlowNavigation.Routes.ShowcaseScreen
            )
        )

    val state = _state.asStateFlow()

    val userProfile = userProfileRepository.userProfile

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