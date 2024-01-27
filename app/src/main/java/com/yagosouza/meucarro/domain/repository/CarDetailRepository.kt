package com.yagosouza.meucarro.domain.repository

import com.yagosouza.meucarro.domain.model.CarDetail

interface CarDetailRepository {

    suspend fun getCarDetail(id: Long): CarDetail

}