package com.cookieanalyzer

import com.cookieanalyzer.cli.CookieAnalyzerCommand
import com.cookieanalyzer.service.CookieAnalyzerService
import com.cookieanalyzer.service.CookieAnalyzerServiceInterface
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

class CookieAnalyzerCliTest {

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
        val targetDate = "2018-12-09"

        // Capture output
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // When
        CookieAnalyzerCommand(service).main(arrayOf("-f", logFile.absolutePath, "-d", targetDate))

        // Then
        val output = outputStream.toString().trim()
        assertEquals("AtY0laUfhglK3lC7", output)
    }
}
