package com.yagosouza.meucarro.presentation.detail

sealed class DetailAction {
    data class ShowToastDescripton(val description: String) : DetailAction()
    object NavigateToHome : DetailAction()
}