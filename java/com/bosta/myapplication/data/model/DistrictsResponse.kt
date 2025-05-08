package com.bosta.myapplication.data.model

data class DistrictsResponse(
    val success: Boolean,
    val message: String,
    val data: List<CityDistrictData>
)


data class CityWithDistricts(
    val cityId: String,
    val cityName: String,
    val districts: List<District>
)

