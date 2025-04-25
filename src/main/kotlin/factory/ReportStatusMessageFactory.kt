package com.report.utils.factory

import com.ms.report.api.repository.entity.enums.ReportStatus
import com.report.utils.factory.dto.ReportStatusMessage

fun getReportStatusMessage(status: ReportStatus): ReportStatusMessage {
    return when (status) {
        ReportStatus.PENDING -> createPendingMessage()
        ReportStatus.PROCESSING -> createProcessingMessage()
        ReportStatus.COMPLETED -> createCompletedMessage()
        ReportStatus.FAILED -> createFailedMessage()
    }
}

fun createGenericMessage(message: String, status: ReportStatus) =
    ReportStatusMessage(status, message)

fun createPendingMessage() =
    ReportStatusMessage(ReportStatus.PENDING, "The report is waiting to be processed.")

fun createProcessingMessage() =
    ReportStatusMessage(ReportStatus.PROCESSING, "The report is being processed.")

fun createCompletedMessage() =
    ReportStatusMessage(ReportStatus.COMPLETED, "The report has been successfully generated.")

fun createFailedMessage() =
    ReportStatusMessage(ReportStatus.FAILED, "The report processing has failed.")
