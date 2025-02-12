package shhashi.practice.i20250126.infrastructure.entity

import java.time.OffsetDateTime

data class RegistrationCode(
    val registrationCodeId: Int? = null,
    val registrationCode: String? = null,
    val createAccountUlid: String? = null,
    val accountUlid: String? = null,
    val expiredIn: OffsetDateTime? = null,
    val createdAt: OffsetDateTime? = null,
)
