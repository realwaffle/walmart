package com.example.walmart.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.data.Country
import com.example.walmart.databinding.CountryRowBinding

class CountriesRecyclerAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountriesRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CountryRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.code.text = country.code.ifEmpty { "code N/A" }
            val name = country.name.ifEmpty { "country N/A" }
            val region = country.region.ifEmpty { "region N/A" }
            val nameRegionString = "$name , $region"
            binding.nameRegionText.text = nameRegionString
            binding.capital.text = country.capital.ifEmpty { "capital N/A" }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryRowBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position])
    }
}