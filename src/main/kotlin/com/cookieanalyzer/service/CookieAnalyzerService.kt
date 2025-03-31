package com.cookieanalyzer.service

import com.cookieanalyzer.domain.CookieLog
import java.io.File
import java.time.LocalDate

class CookieAnalyzerService : CookieAnalyzerServiceInterface {

    override fun findMostActiveCookiesForDate(
        logFile: File,
        targetDate: LocalDate
    ): List<String> {
        val cookiesForDate = parseAndFilterLogs(logFile, targetDate)

        if (cookiesForDate.isEmpty()) {
            return emptyList()
        }

        val cookieCounts = HashMap<String, Int>()
        var maxCount = 0

        for (cookie in cookiesForDate) {
            val count = (cookieCounts[cookie] ?: 0) + 1
            cookieCounts[cookie] = count
            maxCount = maxOf(maxCount, count)
        }

        return cookieCounts.filterValues { it == maxCount }.keys.toList()
    }

    private fun parseAndFilterLogs(logFile: File, targetDate: LocalDate): List<String> {
        return logFile.useLines { lines ->
            lines
                .drop(1) // Skip header line
                .mapNotNull { line ->
                    try {
                        CookieLog.fromCsvLine(line)
                    } catch (e: Exception) {
                        null // Skip invalid lines
                    }
                }
                .takeWhile { log -> log.timestamp.toLocalDate() >= targetDate }
                .filter { log -> log.timestamp.toLocalDate() == targetDate }
                .map { it.cookie }
                .toList()
        }
    }
}
