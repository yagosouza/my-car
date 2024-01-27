package com.yagosouza.meucarro.domain.repository

import com.yagosouza.meucarro.domain.model.Car

interface CarRepository {

    suspend fun getCars(): List<Car>
}
