package com.example.jetstocks.di

import com.example.jetstocks.data.csv.CSVParser
import com.example.jetstocks.data.csv.CompanyListingParser
import com.example.jetstocks.data.repository.StockRepositoryImpl
import com.example.jetstocks.domain.model.CompanyListing
import com.example.jetstocks.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(
        companyListingParser: CompanyListingParser
    ): CSVParser<CompanyListing>


    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

}