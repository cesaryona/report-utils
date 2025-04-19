package com.report.utils.repository.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_report_history")
data class ReportHistoryEntity(

    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    val report: ReportEntity,

    @Column(name = "message")
    val message: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()

)