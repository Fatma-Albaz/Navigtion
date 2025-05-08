package com.bosta.myapplication.data.repository

import com.bosta.myapplication.data.model.CityDistrictData
import com.bosta.myapplication.data.network.ApiService

class DistrictRepository(private val apiService: ApiService) {
    suspend fun getDistricts(countryId: String): List<CityDistrictData> {
        val response = apiService.getDistricts(countryId)
        return response.data
    }
}


