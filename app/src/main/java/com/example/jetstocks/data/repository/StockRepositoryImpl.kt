package com.example.jetstocks.data.repository

import com.example.jetstocks.data.csv.CSVParser
import com.example.jetstocks.data.csv.CompanyListingParser
import com.example.jetstocks.data.local.StockDatabase
import com.example.jetstocks.data.mapper.toCompanyListing
import com.example.jetstocks.data.mapper.toCompanyListingEntity
import com.example.jetstocks.data.remote.StockApi
import com.example.jetstocks.domain.model.CompanyListing
import com.example.jetstocks.domain.repository.StockRepository
import com.example.jetstocks.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val database: StockDatabase,
    val companyListingParser: CSVParser<CompanyListing>
) :
    StockRepository {

    private val dao = database.dao

    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListing(query)
            emit(Resource.Success(data = localListing.map { it.toCompanyListing() }))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                //Todo : Get the CSV file and convert it which is going to be in another function because this method / function
                // is only for the getting the list from cache or network call
                // SINGLE FUNCTION FOR SINGLE WORK
                companyListingParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Unable to download listing"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Unable to download listing"))
                null
            }


            //TODO: Use single single source of truth
            // means populating data from the api and database as well
            // get the data from api insert that data to the database and then populate data from database only
            remoteListings?.let { listing ->
                dao.cleanCompanyListing()
                dao.insertCompanyListing(listing.map { it.toCompanyListingEntity() })
                emit(Resource.Loading(false))
                emit(
                    Resource.Success(
                        data = dao.searchCompanyListing("").map { it.toCompanyListing() })
                )
            }
        }
    }
}