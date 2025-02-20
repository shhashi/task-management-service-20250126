package shhashi.practice.i20250126.infrastructure.repository.entity

import java.time.OffsetDateTime

data class AccountRole(
    val accountRoleId: Int? = null,
    val accountId: Int? = null,
    val projectId: String? = null,
    val roleId: String? = null,
    val createdAt: OffsetDateTime? = null,
    val deletedAt: OffsetDateTime? = null,
)
