package com.report.utils.repository

import com.report.utils.repository.entity.ReportHistoryEntity
import java.util.*

interface ReportHistoryRepository {

    fun save(history: ReportHistoryEntity): ReportHistoryEntity
    fun findAllByReportId(reportId: UUID): List<ReportHistoryEntity>

}