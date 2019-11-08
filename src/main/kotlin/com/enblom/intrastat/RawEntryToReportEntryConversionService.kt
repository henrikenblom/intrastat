package com.enblom.intrastat

import com.enblom.intrastat.gks.domain.RawEntry
import com.enblom.intrastat.report.domain.ReportEntry
import com.enblom.intrastat.util.CountryCodes
import org.springframework.stereotype.Service


@Service
class RawEntryToReportEntryConversionService {

    val ARTICLE_CODE = mapOf("blad" to 49011000,
            "poster" to 49119100,
            "broschyr" to 49011000,
            "väggvepa" to 49119100,
            "hårdband" to 49019100,
            "flyers" to 49011000,
            "vykort" to 49090000,
            "folder" to 49011000,
            "katalog" to 49111010)

    val countryCodes = CountryCodes()

    fun convert(rawEntry: RawEntry): ReportEntry {

        val transactionCode = if (rawEntry.value > 0) 1 else 2
        val description = if (rawEntry.description.isNullOrBlank()) "" else rawEntry.description

        return ReportEntry(rawEntry.id,
                resolveArticleCode(description),
                description,
                countryCodes.getCode(rawEntry.country),
                transactionCode,
                rawEntry.weight,
                rawEntry.value,
                rawEntry.date)
    }

    fun resolveArticleCode(description: String): Int {
        var code = 49119900
        ARTICLE_CODE.forEach { t, u ->
            if (description.contains(t, true)) {
                code = u
                return@forEach
            }
        }
        return code
    }

}