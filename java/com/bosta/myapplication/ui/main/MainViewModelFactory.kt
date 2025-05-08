package com.bosta.myapplication.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bosta.myapplication.data.repository.CityRepository
import com.bosta.myapplication.data.repository.DistrictRepository

class MainViewModelFactory(
    private val cityRepository: CityRepository,
    private val districtRepository: DistrictRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(cityRepository, districtRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


