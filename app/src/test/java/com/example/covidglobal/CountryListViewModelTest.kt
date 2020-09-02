package com.example.covidglobal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.covidglobal.countrylist.CountryListEvent
import com.example.covidglobal.countrylist.CountryListViewModel
import com.example.covidglobal.models.toCountryUIModel
import com.example.domain.Country
import com.example.domain.ResourceException
import com.example.usecases.GetCountries
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Akis on 01/09/2020.
 */
@RunWith(MockitoJUnitRunner::class)
class CountryListViewModelTest {

    @Mock
    private lateinit var event: CountryListEvent

    private lateinit var viewModel: CountryListViewModel

    private lateinit var viewModelSpy: CountryListViewModel


    @Mock
    private lateinit var getCountries: GetCountries

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CountryListViewModel(getCountries)
        viewModelSpy = spy(viewModel)
    }

    @Test
    fun onStart_loadCountries() {
        viewModelSpy.start()

        Mockito.verify(viewModelSpy).loadCountries()
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun loadCountries_showContent() {
        // given
//         var country = Country(
//            countryName = "countryName",
//            newConfirmed = 0,
//            totalConfirmed = 0,
//            date = "2020-09-01T20:34:23Z",
//            countryCode = "countryCode"
//        )

        val country = mock<Country>()

        val countries = listOf(country)

        whenever(getCountries.execute(anyOrNull(), any(), any())).thenAnswer { invocation ->
            val callback =
                invocation.arguments[USE_CASE_ON_SUCCESS_ARGUMENT] as (List<Country>) -> Unit
            callback.invoke(countries)
        }

        // when
        viewModelSpy.loadCountries()

        // then
        assertEquals(viewModel.getEvents().value, CountryListEvent.ShowContent)

    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun loadCountries_showGeneralError() {
        // given
        val resourceException: ResourceException = ResourceException.RemoteResponseError("")

        whenever(getCountries.execute(anyOrNull(), any(), any())).thenAnswer { invocation ->
            val callback =
                invocation.arguments[USE_CASE_ON_ERROR_ARGUMENT] as (ResourceException?) -> Unit
            callback.invoke(resourceException)
        }

        // when
        viewModelSpy.loadCountries()

        // then
        assertEquals(viewModel.getEvents().value, CountryListEvent.ShowGeneralError)
    }

    @Test
    fun onCountryItemClicked_showCountryDetailsScreen() {
        val countryUI = mock<Country>().toCountryUIModel()

        // when
        viewModelSpy.onCountryItemClicked(countryUI)

        // then
        assertEquals(
            viewModel.getEvents().value,
            CountryListEvent.ShowCountryDetailsScreen(countryUI)
        )

    }

    companion object {
        private const val USE_CASE_ON_SUCCESS_ARGUMENT = 1
        private const val USE_CASE_ON_ERROR_ARGUMENT = 2
    }
}