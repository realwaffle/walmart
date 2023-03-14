package com.example.walmart.network

import com.example.walmart.data.Country
import retrofit2.http.GET

interface CountryApi {

    @GET("countries.json")
    suspend fun getCountries(): List<Country>
}