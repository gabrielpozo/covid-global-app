package com.example.covidglobal.countrylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.covidglobal.R
import com.example.covidglobal.adapters.CountriesAdapter
import com.example.covidglobal.models.CountryUI
import com.example.data.CountryRepository
import com.example.usecases.GetCountries
import kotlinx.android.synthetic.main.activity_main.*

class CountryListActivity : AppCompatActivity() {

    private val viewModelFactory = CountryListViewModelFactory(
        GetCountries(CountryRepository.getInstance())
    )
    private lateinit var viewModel: CountryListViewModel
    private lateinit var countriesAdapter: CountriesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CountryListViewModel::class.java)

        initAdapter()
        setupObservers()

        viewModel.start()
    }

    private fun setupObservers() {
        viewModel.getCountries().observe(this, Observer {
            countriesAdapter.submitList(it)
        })
        viewModel.getEvents().observe(this, Observer { event ->
            when (event) {
                CountryListEvent.ShowContent -> showCountries()
                //CountryListEvent.ShowEmptyListError -> showEmptyListError()
                //CountryListEvent.ShowGeneralError -> showGeneralError()
               // is CountryListEvent.ShowCountryDetailsScreen -> showcountryDetailsScreen(event.country)
            }
        })
    }

    private fun showCountries() {
       // loading_error_view.finishLoading {
            countries_recyclerview.visibility = View.VISIBLE
       // }
    }

    private fun showEmptyListError() {
        // implement retry button
    }

    private fun showGeneralError() {
        // implement retry button
    }

    private fun showCountryDetailScreen() {
        //navigate to details
    }

    private fun initAdapter() {
       countriesAdapter = CountriesAdapter(
           object: CountriesAdapter.Listener {
               override fun onClick(country: CountryUI) {
                   viewModel.onCountryItemClicked(country)
               }
           }
       )

        countries_recyclerview.adapter = countriesAdapter
    }
}