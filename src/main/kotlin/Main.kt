import com.cookieanalyzer.cli.CookieAnalyzerCommand
import com.cookieanalyzer.service.CookieAnalyzerService

fun main(args: Array<String>) {
    val cookieAnalyzerService = CookieAnalyzerService()
    CookieAnalyzerCommand(cookieAnalyzerService).main(args)
}
