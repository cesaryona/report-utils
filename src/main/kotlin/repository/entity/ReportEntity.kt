package com.report.utils.repository.entity

import com.ms.report.api.repository.entity.enums.ReportCategory
import com.ms.report.api.repository.entity.enums.ReportStatus
import com.ms.report.api.repository.entity.enums.ReportType
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_report")
data class ReportEntity(

    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "user_id")
    val userId: UUID,

    @OneToMany(mappedBy = "report", cascade = [CascadeType.ALL], orphanRemoval = true)
    val reportHistory: List<ReportHistoryEntity> = mutableListOf(),

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    val type: ReportType,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    val status: ReportStatus = ReportStatus.PENDING,

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    val category: ReportCategory,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "s3_location")
    val s3Locatation: String? = null
)