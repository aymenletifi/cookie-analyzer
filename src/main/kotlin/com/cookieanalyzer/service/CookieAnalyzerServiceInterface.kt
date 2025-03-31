package com.cookieanalyzer.service

import java.io.File
import java.time.LocalDate

interface CookieAnalyzerServiceInterface {
    fun findMostActiveCookiesForDate(logFile: File, targetDate: LocalDate): List<String>
}
