package com.manubla.cinemapp.data.helper.converter

import androidx.room.TypeConverter
import com.manubla.cinemapp.data.helper.dateFormat
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String) =
        LocalDate.parse(value, DateTimeFormatter.ofPattern(dateFormat))
            .atStartOfDay(ZoneId.systemDefault())

    @TypeConverter
    fun dateToTimestamp(date: ZonedDateTime) =
        DateTimeFormatter.ofPattern(dateFormat).format(date)
}