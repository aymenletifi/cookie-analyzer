package com.cookieanalyzer.cli

import com.cookieanalyzer.service.CookieAnalyzerServiceInterface
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class CookieAnalyzerCommand(
    private val service: CookieAnalyzerServiceInterface
) : CliktCommand(
    name = "cookie-analyzer",
    help = "Analyzes cookie logs to find the most active cookies for a given date"
) {
    private val file by option("-f", "--file", help = "Log file to process")
        .required()

    private val date by option("-d", "--date", help = "Date to analyze (YYYY-MM-DD)")
        .required()

    override fun run() {
        try {
            val logFile = File(file)
            if (!logFile.exists()) {
                echo("Error: File not found: $file", err = true)
                return
            }

            val targetDate = try {
                LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE)
            } catch (e: DateTimeParseException) {
                echo("Error: Invalid date format. Please use YYYY-MM-DD", err = true)
                return
            }

            val cookies = service.findMostActiveCookiesForDate(logFile, targetDate)

            if (cookies.isEmpty()) {
                echo("No cookies found for the specified date")
            } else {
                cookies.forEach { cookie ->
                    echo(cookie)
                }
            }
        } catch (e: Exception) {
            echo("An unexpected error occurred: ${e.message}", err = true)
        }
    }
}
