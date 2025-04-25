package com.report.utils.factory.dto

import com.ms.report.api.repository.entity.enums.ReportStatus

data class ReportStatusMessage(

    val status: ReportStatus,
    val message: String,

)