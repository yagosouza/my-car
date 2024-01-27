package com.yagosouza.meucarro.domain.usecase

import com.yagosouza.meucarro.domain.model.CarDetail
import com.yagosouza.meucarro.domain.repository.CarDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCarDetailUseCaseImpl(
    private val repository: CarDetailRepository
) : GetCarDetailUseCase {

    override fun invoke(id: Long): Flow<CarDetail> = flow {
        val car = repository.getCarDetail(id)
        emit(car)
    }
}