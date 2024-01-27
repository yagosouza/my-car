package com.yagosouza.meucarro.data.repository

import com.yagosouza.meucarro.data.api.CarApi
import com.yagosouza.meucarro.data.mappers.toDomain
import com.yagosouza.meucarro.domain.model.CarDetail
import com.yagosouza.meucarro.domain.repository.CarDetailRepository

class CarDetailRepositoryImpl(
    private val api: CarApi
): CarDetailRepository {

    override suspend fun getCarDetail(id: Long): CarDetail {
        return  api.getCarDetail(id).toDomain()
    }
}