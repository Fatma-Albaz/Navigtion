package com.bosta.myapplication.data.repository

import com.bosta.myapplication.data.model.City
import com.bosta.myapplication.data.network.ApiService

class CityRepository(private val apiService: ApiService) {
    suspend fun getCities(): List<City> {
        val response = apiService.getCities()
        return response.data.list
    }
}

