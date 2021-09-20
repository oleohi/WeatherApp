package com.oleohialli.weatherapp.views

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oleohialli.weatherapp.R
import com.oleohialli.weatherapp.databinding.FragmentWeatherBinding
import com.oleohialli.weatherapp.utils.Resource
import com.oleohialli.weatherapp.utils.extensions.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentWeatherBinding.bind(view)

        val weatherAdapter = WeatherAdapter()

        binding.apply {
            weatherRecyclerView.adapter = weatherAdapter
            weatherRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            weatherRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )

            viewModel.weather.observe(this@WeatherFragment.viewLifecycleOwner) { result ->
                weatherAdapter.submitList(result.data)
                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                errorTextView.isVisible = result is Resource.Error<*> && result.data.isNullOrEmpty()
                errorTextView.text = result.error?.localizedMessage

                println("ddls error: " + result.error?.localizedMessage)
                println("ddls result: " + result.data)

            }

        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.header_menu, menu)

        val searchByCity = menu.findItem(R.id.action_search)
        val searchView = searchByCity.actionView as SearchView

        searchView.onQueryTextChanged {
            viewModel.searchQuery.value = it
        }
    }
}