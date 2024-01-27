package com.yagosouza.meucarro.presentation.detail

import com.yagosouza.meucarro.domain.model.CarDetail

data class DetailState(
    val isLoading: Boolean = false,
    val carDetail: CarDetail? = null
) {

    fun onLoading() = copy(isLoading = true, carDetail = null)

    fun onFinishedLoading() = copy(isLoading = false)

    fun onCarsReceived(carDetail: CarDetail) = copy(carDetail = carDetail)
}