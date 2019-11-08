package com.enblom.intrastat.report.repo

import com.enblom.intrastat.report.domain.ReportEntry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ReportEntryRepo : JpaRepository<ReportEntry, Int>