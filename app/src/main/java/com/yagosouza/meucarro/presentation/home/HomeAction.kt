package com.yagosouza.meucarro.presentation.home

sealed class HomeAction {

    data class ShowCarDetails(val id: Long) : HomeAction()
    object FinishApp : HomeAction()
}