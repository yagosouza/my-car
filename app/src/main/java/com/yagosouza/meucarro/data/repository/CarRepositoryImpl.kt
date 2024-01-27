package com.yagosouza.meucarro.data.repository

import com.yagosouza.meucarro.data.api.CarApi
import com.yagosouza.meucarro.data.mappers.toDomain
import com.yagosouza.meucarro.domain.model.Car
import com.yagosouza.meucarro.domain.repository.CarRepository

class CarRepositoryImpl (
    private val api: CarApi
) : CarRepository {

    override suspend fun getCars(): List<Car> {
        return api.getCars().map { it.toDomain() }
    }
}