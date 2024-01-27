package com.yagosouza.meucarro.data.api

import com.yagosouza.meucarro.data.model.CarDetailResponse
import com.yagosouza.meucarro.data.model.CarResponse

class CarApiImpl : CarApi {

    override suspend fun getCars(): List<CarResponse> =
        listCars

    override suspend fun getCarDetail(idCar: Long): CarDetailResponse {
        var carDetailResponse = CarDetailResponse(id = 1, name = "d", description = "dd")

        listCars.filter { carResponse -> carResponse.id == idCar }.map {
            return CarDetailResponse(
                id = it.id,
                name = it.name,
                description = it.name.plus(it.id)
            )
        }

        return carDetailResponse
    }
}


val listCars = listOf(
    CarResponse(id = 1, name = "Golzão quadrado"),
    CarResponse(id = 2, name = "Uno com escada"),
    CarResponse(id = 3, name = "Fusca"),
    CarResponse(id = 4, name = "Kombi"),
    CarResponse(id = 5, name = "Passat")
)