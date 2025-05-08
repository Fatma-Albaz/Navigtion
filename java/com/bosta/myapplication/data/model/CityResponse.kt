package com.bosta.myapplication.data.model

data class CityResponse(
    val success: Boolean,
    val message: String,
    val data: CityData

)

data class CityData(
    val list: List<City>
)
