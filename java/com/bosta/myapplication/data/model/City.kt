package com.bosta.myapplication.data.model


data class City(
    val _id: String,
    val name: String,
    val nameAr: String?,
    val code: String?,
    val alias: String?,
    val hub: Hub?,
    val sector: Int?,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
    val showAsDropOff: Boolean,
    val showAsPickup: Boolean,
    val districts: List<District> = emptyList() // Default if not provided
)

data class Hub(
    val _id: String,
    val name: String
)


