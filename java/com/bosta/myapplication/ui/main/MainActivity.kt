package com.bosta.myapplication.ui.main

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bosta.myapplication.data.model.City
import com.bosta.myapplication.data.network.ApiService
import com.bosta.myapplication.data.repository.CityRepository
import com.bosta.myapplication.data.repository.DistrictRepository
import com.bosta.myapplication.databinding.ActivityMainBinding

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.AdapterView
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var districtAdapter: DistrictAdapter
    private var cities: List<City> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        // Setup view binding
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        // Setup Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://stg-app.bosta.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        // Setup ViewModel
        val cityRepository = CityRepository(apiService)
        val districtRepository = DistrictRepository(apiService)
        val viewModelFactory = MainViewModelFactory(cityRepository, districtRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setupRecyclerView()
        observeViewModel()
        setupCitySearch()
        viewModel.loadCities()
    }

    private fun setupRecyclerView() {
        districtAdapter = DistrictAdapter()
        binding.recyclerViewDistricts.adapter = districtAdapter
    }

    private fun observeViewModel() {
        // Observe cities for Spinner
        viewModel.cities.observe(this) { cityList ->
            if (cityList.isNullOrEmpty()) {
                Toast.makeText(this, "No cities found", Toast.LENGTH_SHORT).show()
                return@observe
            }

            cities = cityList
            val cityNames = cityList.map { it.name }

            val spinnerAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, cityNames)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCities.adapter = spinnerAdapter

            binding.spinnerCities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val selectedCity = cities[position]
                    viewModel.loadDistrictsForCity(selectedCity)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        // Observe districts for RecyclerView
        viewModel.districts.observe(this) { districtList ->
            districtAdapter.submitList(districtList)
        }
    }
    private fun setupCitySearch() {
        binding.editTextSearchCity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()

                val filteredCities = cities.filter { city ->
                    city.name.contains(query, ignoreCase = true)
                }

                val cityNames = filteredCities.map { it.name }
                val spinnerAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, cityNames)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                binding.spinnerCities.adapter = spinnerAdapter

                // Maintain onItemSelected logic for filtered list
                binding.spinnerCities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                        val selectedCity = filteredCities[position]
                        viewModel.loadDistrictsForCity(selectedCity)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {}
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

}