package com.enblom.intrastat.gks.domain

import java.sql.Date
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "intrastat")
class RawEntry(@Id val id: Int,
               val date: Date,
               val description: String,
               val country: String,
               val weight: Int,
               val value: Int)