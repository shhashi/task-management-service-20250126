package shhashi.practice.i20250126.infrastructure.entity

import java.time.OffsetDateTime

data class Account(
    val accountId: String? = null,
    val loginId: String? = null,
    val accountName: String? = null,
    val password: String? = null,
    val createdAt: OffsetDateTime? = null,
    val deletedAt: OffsetDateTime? = null,
)
