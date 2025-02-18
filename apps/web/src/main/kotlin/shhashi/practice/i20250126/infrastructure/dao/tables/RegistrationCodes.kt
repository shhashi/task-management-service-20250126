package shhashi.practice.i20250126.infrastructure.dao.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestampWithTimeZone

object RegistrationCodes : Table("registration_codes") {
    val registrationCodeId = integer("registration_code_id") // TODO このカラムいらない気がする
    val registrationCode = varchar("registration_code", 32).nullable()
    val createAccountId = integer("create_account_id").nullable()
    val accountId = integer("account_id").nullable()
    val expiredIn = timestampWithTimeZone("expired_in").nullable()
    val createdAt = timestampWithTimeZone("created_at").nullable()
}
