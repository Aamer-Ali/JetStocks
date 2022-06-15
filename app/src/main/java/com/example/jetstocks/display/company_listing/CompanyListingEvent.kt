package com.example.jetstocks.display.company_listing

sealed class CompanyListingEvent {
    object OnRefresh : CompanyListingEvent()
    data class OnSearchQueryChange(val query: String) : CompanyListingEvent()
}
