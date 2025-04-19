package com.ms.report.api.repository

import com.ms.report.api.repository.entity.enums.ReportStatus
import com.report.utils.repository.entity.ReportEntity
import java.util.*


interface ReportRepository {

    fun findById(id: UUID): ReportEntity

    fun save(report: ReportEntity): ReportEntity

    fun findByUserId(userId: UUID)

    fun findAllByStatus(status: ReportStatus): List<ReportEntity>
}