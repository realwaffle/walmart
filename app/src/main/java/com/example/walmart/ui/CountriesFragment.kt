package com.example.walmart.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmart.R
import com.example.walmart.databinding.CountriesFragmentBinding
import com.example.walmart.network.CountryService
import com.example.walmart.viewmodel.CountriesViewModel
import com.example.walmart.viewmodel.CountriesViewModelFactory


class CountriesFragment : Fragment() {

    private val countryApi = CountryService.api

    companion object {
        const val TAG = "CountriesFragment"
    }

    private lateinit var binding: CountriesFragmentBinding

    private val viewModel: CountriesViewModel by viewModels { CountriesViewModelFactory(countryApi) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.countries_fragment, container, false
        )
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            LinearLayout.VERTICAL
        )
        binding.recycler.addItemDecoration(dividerItemDecoration)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCountries()
        viewModel.countriesResult.observe(viewLifecycleOwner) { result ->
            when {
                result.isSuccess -> result.getOrNull()?.let {
                    binding.recycler.adapter = CountriesRecyclerAdapter(it)
                }
                result.isFailure -> {
                    val errorMessage =
                        "Failed to fetch countries List with exception: ${result.exceptionOrNull()?.message}"

                    Toast.makeText(
                        requireContext(),
                        errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e(
                        CountriesViewModel.TAG,
                        errorMessage
                    )
                }
            }
        }
    }
}