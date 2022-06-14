package com.example.jetstocks.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface StockApi {
    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String
    ): ResponseBody


    companion object {
        const val API_KEY = "4SRC2G96I2YYW3IM"
        const val BASE_URL = "https://www.alphavantage.co/"
    }
}