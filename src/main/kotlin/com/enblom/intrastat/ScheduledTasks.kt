package com.enblom.intrastat

import com.enblom.intrastat.gks.repo.RawEntryRepo
import com.enblom.intrastat.report.repo.ReportEntryRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class ScheduledTasks {

    @Autowired
    lateinit var rawEntryRepo: RawEntryRepo

    @Autowired
    lateinit var reportEntryRepo: ReportEntryRepo

    @Autowired
    lateinit var rawEntryToReportEntryConversionService: RawEntryToReportEntryConversionService

    @Scheduled(cron = "\${synchronization.time}")
    fun syncReportData() {
        rawEntryRepo.findAll().stream().forEach {
            val reportEntry = rawEntryToReportEntryConversionService.convert(it)
            reportEntryRepo.save(reportEntry)
            println(reportEntry.toCSVRow())
        }
    }

}