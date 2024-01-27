package com.yagosouza.meucarro.data.api

import com.yagosouza.meucarro.data.model.CarDetailResponse
import com.yagosouza.meucarro.data.model.CarResponse

interface CarApi {

    suspend fun getCars(): List<CarResponse>

    suspend fun getCarDetail(id: Long): CarDetailResponse
}
