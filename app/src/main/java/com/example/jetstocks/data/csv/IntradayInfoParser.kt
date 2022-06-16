package com.example.jetstocks.data.csv

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.jetstocks.data.mapper.toIntradayInfo
import com.example.jetstocks.data.remote.dto.IntradayInfoDto
import com.example.jetstocks.domain.model.IntradayInfo
import com.opencsv.CSVReader
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntradayInfoParser @Inject constructor() : CSVParser<IntradayInfo> {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun parse(stream: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return csvReader
            .readAll()
            .drop(1)
            .mapNotNull { line ->
                val timeStamp = line.getOrNull(0) ?: return@mapNotNull null
                val close = line.getOrNull(4) ?: return@mapNotNull null
                //TODO:Cannot Directly add to IntradayInfo Because time is of LocalDateTime here and we get String
                // For that add to dto and then add to Model
                val dto = IntradayInfoDto(timeStamp, close.toDouble())
                dto.toIntradayInfo()
            }
            .filter {
                //TODO: Filter Data to show the last Day
                it.date.dayOfMonth == LocalDateTime.now().minusDays(1).dayOfMonth
            }.sortedBy {
                it.date.hour
            }
            .also { csvReader.close() }
    }

}