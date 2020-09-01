package com.example.covidglobal.countrydetails

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.covidglobal.R
import com.example.covidglobal.databinding.ActivityCountryDetailsBinding
import com.example.covidglobal.general.BaseActivity
import com.example.covidglobal.models.CountryUI

class CountryDetailsActivity : BaseActivity() {

    private lateinit var viewModel: CountryDetailsViewModel
    private var _binding: ActivityCountryDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_country_details)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(CountryDetailsViewModel::class.java)
        setupObservers()

        var country: CountryUI? = null
        if (intent.hasExtra(COUNTRY_EXTRA)) {
            country = intent.getParcelableExtra(COUNTRY_EXTRA)
        }

        viewModel.start(country)
    }

    private fun setupObservers() {
        viewModel.getCountry().observe(this, Observer {
            binding.country = it
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val COUNTRY_EXTRA = "COUNTRY_EXTRA"

        fun start(activity: Activity, country: CountryUI) {
            val starter = Intent(activity, CountryDetailsActivity::class.java)
            starter.putExtra(COUNTRY_EXTRA, country)
            activity.startActivity(starter)
        }
    }
}