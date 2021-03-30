package com.example.tsudatech.habit.common

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjuster

class DateTimeUtility {

    companion object {
        private val YYYYMMDD_FORMATTER : DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss")

        fun fromYYYYMMDD(yyyyMMdd : String) : LocalDateTime {
            return LocalDateTime.parse("$yyyyMMdd 00:00:00", YYYYMMDD_FORMATTER).with(LocalTime.MIN)
        }

        fun fromYYYYMMDDWithMaxTime(yyyyMMdd : String) : LocalDateTime {
            return LocalDateTime.parse("$yyyyMMdd 00:00:00", YYYYMMDD_FORMATTER).with(LocalTime.MAX)
        }

        fun fromYYYYMMDDWithAdjuster(yyyyMMdd : String, adjuster : TemporalAdjuster) : LocalDateTime {
            return LocalDateTime.parse("$yyyyMMdd 00:00:00", YYYYMMDD_FORMATTER).with(adjuster)
        }
    }
}