package com.yagosouza.meucarro.domain.usecase

import com.yagosouza.meucarro.domain.model.Car
import kotlinx.coroutines.flow.Flow

interface GetCarsUseCase {

    operator fun invoke(): Flow<List<Car>>
}
