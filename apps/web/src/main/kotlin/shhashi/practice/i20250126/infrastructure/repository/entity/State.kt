package shhashi.practice.i20250126.infrastructure.repository.entity

import java.time.OffsetDateTime

data class State(
    val statusId: Int? = null,
    val statusName: String? = null,
    val createdAt: OffsetDateTime? = null,
    val deletedAt: OffsetDateTime? = null,
)
