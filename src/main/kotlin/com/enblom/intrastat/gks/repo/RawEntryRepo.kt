package com.enblom.intrastat.gks.repo

import com.enblom.intrastat.gks.domain.RawEntry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface RawEntryRepo : JpaRepository<RawEntry, Int>