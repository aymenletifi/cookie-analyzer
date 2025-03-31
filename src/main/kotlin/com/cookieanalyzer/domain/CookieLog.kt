package com.cookieanalyzer.domain

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class CookieLog(
    val cookie: String,
    val timestamp: ZonedDateTime
) {
    companion object {
        private val TIMESTAMP_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME

        fun fromCsvLine(line: String): CookieLog {
            val parts = line.split(",")
            if (parts.size != 2) {
                throw IllegalArgumentException("Invalid log format: $line")
            }

            val cookie = parts[0].trim()
            val timestamp = ZonedDateTime.parse(parts[1].trim(), TIMESTAMP_FORMATTER)

            return CookieLog(cookie, timestamp)
        }
    }
}
