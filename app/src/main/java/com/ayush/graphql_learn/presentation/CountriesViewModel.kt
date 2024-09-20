package com.ayush.graphql_learn.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.graphql_learn.domain.DetailedCountry
import com.ayush.graphql_learn.domain.GetCountriesUseCase
import com.ayush.graphql_learn.domain.GetCountryUseCase
import com.ayush.graphql_learn.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase,
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val countries = getCountriesUseCase()
            _state.update {
                it.copy(countries = countries, isLoading = false)
            }
        }
    }

    fun onCountrySelected(code: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val country = getCountryUseCase(code)
            _state.update {
                it.copy(detailCountry = country, isLoading = false)
            }
        }
    }

    fun onCountryDetailDismissed() {
        _state.update {
            it.copy(detailCountry = null)
        }
    }

    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading : Boolean = false,
        val detailCountry: DetailedCountry? = null
    )
}
