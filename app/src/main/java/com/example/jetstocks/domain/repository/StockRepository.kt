package com.example.jetstocks.domain.repository

import com.example.jetstocks.domain.model.CompanyListing
import com.example.jetstocks.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListing(
        fetchFromRemote: Boolean = false,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

}