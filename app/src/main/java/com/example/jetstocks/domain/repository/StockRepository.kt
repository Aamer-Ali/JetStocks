package com.example.jetstocks.domain.repository

import com.example.jetstocks.domain.model.CompanyInfo
import com.example.jetstocks.domain.model.CompanyListing
import com.example.jetstocks.domain.model.IntradayInfo
import com.example.jetstocks.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListing(
        fetchFromRemote: Boolean = false,
        query: String
    ): Flow<Resource<List<CompanyListing>>>


    suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo>

}