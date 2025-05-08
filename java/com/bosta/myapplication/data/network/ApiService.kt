package com.bosta.myapplication.data.network
import com.bosta.myapplication.data.model.CityResponse
import com.bosta.myapplication.data.model.DistrictResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("cities")
    suspend fun getCities(): CityResponse

    @GET("cities/getAllDistricts")
    suspend fun getDistricts(
        @Query("countryId") countryId: String
    ): DistrictResponse
}

