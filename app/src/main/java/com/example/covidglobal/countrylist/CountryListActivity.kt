package com.example.covidglobal.countrylist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.covidglobal.R
import com.example.covidglobal.adapters.CountriesAdapter
import com.example.covidglobal.countrydetails.CountryDetailsActivity
import com.example.covidglobal.general.BaseActivity
import com.example.covidglobal.models.CountryUI
import com.example.data.CountryRepository
import com.example.usecases.GetCountriesUseCase
import kotlinx.android.synthetic.main.layout_country_list.*
import kotlinx.android.synthetic.main.layout_error_view.*

class CountryListActivity : BaseActivity() {

    private val viewModelFactory = CountryListViewModelFactory(
        GetCountriesUseCase(CountryRepository.getInstance())
    )
    private lateinit var viewModel: CountryListViewModel
    private lateinit var countriesAdapter: CountriesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_country_list)
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
                CountryListEvent.ShowGeneralError -> showGeneralError()
                is CountryListEvent.ShowCountryDetailsScreen -> showCountryDetailScreen(event.country)
            }
        })
    }

    private fun showCountries() {
        countries_recyclerview.visibility = View.VISIBLE

    }

    private fun showGeneralError() {
        error_view.visibility = View.VISIBLE
        error_button.setOnClickListener {
            viewModel.onRetryButtonPressed()
            error_view.visibility = View.GONE
        }
    }

    private fun showCountryDetailScreen(country: CountryUI) {
        CountryDetailsActivity.start(this, country)
    }

    private fun initAdapter() {
        countriesAdapter = CountriesAdapter(
            object : CountriesAdapter.Listener {
                override fun onClick(country: CountryUI) {
                    viewModel.onCountryItemClicked(country)
                }
            }
        )

        countries_recyclerview.adapter = countriesAdapter
    }
}