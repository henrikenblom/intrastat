package com.enblom.intrastat.report.domain

import java.sql.Date
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "report")
class ReportEntry(@Id val id: Int,
                  val articleCode: Int = 49119900,
                  val description: String = "",
                  val countryCode: String = "SE",
                  val transactionCode: Int,
                  val weight: Int = 0,
                  val value: Int,
                  val transactionDate: Date) {

    override fun toString(): String {
        return "ReportEntry(id=$id, articleCode=$articleCode, description='$description', countryCode='$countryCode', transactionCode=$transactionCode, weight=$weight, value=$value, transactionDate=$transactionDate)"
    }

    fun toCSVRow(): String {
        return "$transactionDate,$articleCode,$description,$countryCode,$transactionCode,$weight,$value"
    }

}