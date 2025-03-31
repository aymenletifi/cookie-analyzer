package com.cookieanalyzer.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CookieLogTest {

    @Test
    fun `should parse valid log line`() {
        // Given
        val line = "AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00"

        // When
        val cookieLog = CookieLog.fromCsvLine(line)

        // Then
        assertEquals("AtY0laUfhglK3lC7", cookieLog.cookie)
        assertEquals(
            ZonedDateTime.parse("2018-12-09T14:19:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            cookieLog.timestamp
        )
    }

    @Test
    fun `should throw exception for invalid log format`() {
        // Given
        val line = "InvalidLine"

        // When Then
        assertThrows<IllegalArgumentException> {
            CookieLog.fromCsvLine(line)
        }
    }

    @Test
    fun `should throw exception for invalid timestamp`() {
        // Given
        val line = "AtY0laUfhglK3lC7,InvalidTimestamp"

        // When Then
        assertThrows<Exception> {
            CookieLog.fromCsvLine(line)
        }
    }
}
