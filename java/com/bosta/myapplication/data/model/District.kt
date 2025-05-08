package com.bosta.myapplication.data.model


data class DistrictResponse(
    val success: Boolean,
    val message: String,
    val data: List<CityDistrictData>
)

data class CityDistrictData(
    val cityId: String,
    val cityName: String,
    val districts: List<District>
)

data class District(
    val districtId: String,
    val districtName: String,
    val districtOtherName: String,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean
)
