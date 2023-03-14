package com.example.walmart.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.walmart.data.Country
import com.example.walmart.network.CountryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountriesViewModel(private val countryApi: CountryApi) : ViewModel() {
    companion object {
        const val TAG = "CountriesViewModel"
    }

    private val _countriesResult = MutableLiveData<Result<List<Country>>>()
    val countriesResult: LiveData<Result<List<Country>>> = _countriesResult

    fun getCountries() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { runCatching { countryApi.getCountries() } }
            _countriesResult.value = result
        }
    }

}

class CountriesViewModelFactory(
    private val countryApi: CountryApi
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = CountriesViewModel(countryApi) as T
}