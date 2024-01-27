package com.yagosouza.meucarro.data.mappers

import com.yagosouza.meucarro.data.model.CarDetailResponse
import com.yagosouza.meucarro.data.model.CarResponse
import com.yagosouza.meucarro.domain.model.Car
import com.yagosouza.meucarro.domain.model.CarDetail

fun CarResponse.toDomain() = Car(id = id, name = name)

fun CarDetailResponse.toDomain() = CarDetail(id = id, name = name, description = description)