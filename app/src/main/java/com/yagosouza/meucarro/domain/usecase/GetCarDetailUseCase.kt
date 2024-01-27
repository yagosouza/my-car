package com.yagosouza.meucarro.domain.usecase

import com.yagosouza.meucarro.domain.model.CarDetail
import kotlinx.coroutines.flow.Flow

interface GetCarDetailUseCase {

    operator fun invoke(id: Long): Flow<CarDetail>

}