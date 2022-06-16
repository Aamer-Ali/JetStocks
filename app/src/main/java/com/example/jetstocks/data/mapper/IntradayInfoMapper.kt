package com.example.jetstocks.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.jetstocks.data.remote.dto.IntradayInfoDto
import com.example.jetstocks.domain.model.IntradayInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun IntradayInfoDto.toIntradayInfo(): IntradayInfo {
    val datePattern = "yyyy-MM-dd hh:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(datePattern, Locale.getDefault())
    val formattedDate = LocalDateTime.parse(time, formatter)
    return IntradayInfo(formattedDate, close)
}