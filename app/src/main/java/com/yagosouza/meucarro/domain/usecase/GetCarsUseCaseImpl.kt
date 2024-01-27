package com.yagosouza.meucarro.domain.usecase

import com.yagosouza.meucarro.domain.model.Car
import com.yagosouza.meucarro.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCarsUseCaseImpl(
    private val repository: CarRepository
) : GetCarsUseCase {

    override fun invoke(): Flow<List<Car>> = flow {
        val cars = repository.getCars()
        emit(cars)
    }
}