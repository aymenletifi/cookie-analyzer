package com.cookieanalyzer.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.time.LocalDate

class CookieAnalyzerServiceTest {

    private lateinit var service: CookieAnalyzerServiceInterface
    private val basePath = "src/test/resources"

    @BeforeEach
    fun setup() {
        service = CookieAnalyzerService()
    }

    @Test
    fun `should return most active cookies for given date`() {
        // Given
        val logFile = File("$basePath/most_active_cookies.csv")

        val targetDate = LocalDate.of(2018, 12, 9)

        // When
        val result = service.findMostActiveCookiesForDate(logFile, targetDate)

        // Then
        assertEquals(listOf("AtY0laUfhglK3lC7"), result)
    }

    @Test
    fun `should return multiple cookies if they have the same count`() {
        // Given
        val logFile = File("$basePath/multiple_most_active_cookies.csv")

        val targetDate = LocalDate.of(2018, 12, 9)

        // When
        val result = service.findMostActiveCookiesForDate(logFile, targetDate).sorted()

        // Then
        assertEquals(listOf("AtY0laUfhglK3lC7", "SAZuXPGUrfbcn5UA").sorted(), result)
    }

    @Test
    fun `should return empty list when no cookies for given date`() {
        // Given
        val logFile = File("$basePath/no_cookies_for_date.csv")

        val targetDate = LocalDate.of(2018, 12, 10)

        // When
        val result = service.findMostActiveCookiesForDate(logFile, targetDate)

        // Then
        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `should handle invalid log entries`() {
        // Given
        val logFile = File("$basePath/invalid_entries.csv")

        val targetDate = LocalDate.of(2018, 12, 9)

        // When
        val result = service.findMostActiveCookiesForDate(logFile, targetDate)

        // Then
        assertEquals(listOf("AtY0laUfhglK3lC7", "SAZuXPGUrfbcn5UA").sorted(), result.sorted())
    }

    @Test
    fun `should handle empty log file`() {
        // Given
        val logFile = File("$basePath/empty_file.csv")

        // When
        val targetDate = LocalDate.of(2018, 12, 9)
        val result = service.findMostActiveCookiesForDate(logFile, targetDate)

        // Then
        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `should handle log file with only headers`() {
        // Given
        val logFile = File("$basePath/only_headers.csv")

        // When
        val targetDate = LocalDate.of(2018, 12, 9)
        val result = service.findMostActiveCookiesForDate(logFile, targetDate)

        // Then
        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `should handle single log entry`() {
        // Given
        val logFile = File("$basePath/single_entry.csv")

        // When
        val targetDate = LocalDate.of(2018, 12, 9)
        val result = service.findMostActiveCookiesForDate(logFile, targetDate)

        // Then
        assertEquals(listOf("AtY0laUfhglK3lC7"), result)
    }
}
