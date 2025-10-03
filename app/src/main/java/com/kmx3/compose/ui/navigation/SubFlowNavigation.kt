package com.kmx3.compose.ui.navigation

import androidx.navigation.NavGraphBuilder

abstract class SubFlowNavigation(val onFinished: (routeName: String) -> Unit) {
    abstract val startRoute: String

    abstract fun addFlow(builder: NavGraphBuilder)

    fun finishFlow() {
        onFinished(startRoute)
    }
}