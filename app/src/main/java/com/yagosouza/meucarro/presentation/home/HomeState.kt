package com.yagosouza.meucarro.presentation.home

import com.yagosouza.meucarro.domain.model.Car

data class HomeState(
    val isLoading: Boolean = false,
    val cars: List<Car>? = null
) {

    fun onLoading() = copy(isLoading = true, cars = null)

    fun onFinishedLoading() = copy(isLoading = false)

    fun onCarsReceived(cars: List<Car>) = copy(cars = cars)
}
