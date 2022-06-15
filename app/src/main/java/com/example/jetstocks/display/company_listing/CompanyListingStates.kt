package com.example.jetstocks.display.company_listing

import com.example.jetstocks.domain.model.CompanyListing

data class CompanyListingStates(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefresh: Boolean = false,
    val searchQuery: String = ""

)