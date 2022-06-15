package com.example.jetstocks.display.company_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetstocks.domain.repository.StockRepository
import com.example.jetstocks.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListingViewModel @Inject constructor(private val repository: StockRepository) :
    ViewModel() {


    var states by mutableStateOf(CompanyListingStates())
    private var searchJob: Job? = null


    init {
        getCompanyListing()
    }

    fun onEvent(event: CompanyListingEvent) {
        when (event) {
            is CompanyListingEvent.OnRefresh -> {
                getCompanyListing(fetchFromRemote = true)
            }
            is CompanyListingEvent.OnSearchQueryChange -> {
                states = states.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyListing()
                }
            }
        }
    }


    private fun getCompanyListing(
        query: String = states.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getCompanyListing(fetchFromRemote, query).collect { result ->
                when (result) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> {
                        states = states.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            states = states.copy(companies = listings)
                        }
                    }
                }
            }
        }
    }
}