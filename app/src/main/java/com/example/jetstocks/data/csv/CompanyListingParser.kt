package com.example.jetstocks.data.csv

import com.example.jetstocks.domain.model.CompanyListing
import com.opencsv.CSVReader
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyListingParser @Inject constructor() : CSVParser<CompanyListing> {
    override suspend fun parse(stream: InputStream): List<CompanyListing> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return csvReader
            .readAll()
            .drop(1)
            .mapNotNull { line ->
                val name = line.getOrNull(0)
                val symbol = line.getOrNull(1)
                val exchange = line.getOrNull(2)
                CompanyListing(
                    name = name ?: return@mapNotNull null,
                    symbol = symbol ?: return@mapNotNull null,
                    exchange = exchange ?: return@mapNotNull null
                )
            }.also { csvReader.close() }
    }
}