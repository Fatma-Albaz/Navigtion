package com.bosta.myapplication.ui.main

import androidx.lifecycle.*
import com.bosta.myapplication.data.model.City
import com.bosta.myapplication.data.model.District
import com.bosta.myapplication.data.repository.CityRepository
import com.bosta.myapplication.data.repository.DistrictRepository

import kotlinx.coroutines.launch
class MainViewModel(
    private val cityRepository: CityRepository,
    private val districtRepository: DistrictRepository
) : ViewModel() {

    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> = _cities

    private val _districts = MutableLiveData<List<District>>()
    val districts: LiveData<List<District>> = _districts

    private val countryId = "60e4482c7cb7d4bc4849c4d5"

    fun loadCities() {
        viewModelScope.launch {
            try {
                val result = cityRepository.getCities()
                _cities.value = result
            } catch (e: Exception) {
                //TODO: handle error
            }
        }
    }

    fun loadDistrictsForCity(city: City) {
        viewModelScope.launch {
            try {
                val response = districtRepository.getDistricts(countryId)
                val matchedCity = response.find { it.cityId == city._id }
                _districts.value = matchedCity?.districts ?: emptyList()
            } catch (e: Exception) {
                //TODO: handle error
            }
        }
    }
}

