package shhashi.practice.i20250126.infrastructure.entity

import java.time.OffsetDateTime

data class Project(
    val projectId: String? = null,
    val projectName: String? = null,
    val createdAt: OffsetDateTime? = null,
    val deletedAt: OffsetDateTime? = null,
)
