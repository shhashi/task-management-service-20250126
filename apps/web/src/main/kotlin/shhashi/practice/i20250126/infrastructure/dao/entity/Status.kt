package shhashi.practice.i20250126.infrastructure.dao.entity

import java.time.OffsetDateTime

data class Status(
    val statusId: Int? = null,
    val statusName: String? = null,
    val createdAt: OffsetDateTime? = null,
    val deletedAt: OffsetDateTime? = null,
)
