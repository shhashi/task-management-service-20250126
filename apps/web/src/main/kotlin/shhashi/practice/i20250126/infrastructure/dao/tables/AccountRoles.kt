package shhashi.practice.i20250126.infrastructure.dao.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestampWithTimeZone

// TODO 頑張って外部キー制約を定義する
object AccountRoles : Table("account_roles") {
    val accountRoleId = integer("account_role_id").autoIncrement()
    val accountId = integer("account_id")
    val projectId = varchar("project_id", 100)
    val roleId = varchar("role_id", 100)
    val createdAt = timestampWithTimeZone("created_at").nullable()
    val deletedAt = timestampWithTimeZone("deleted_at").nullable()
}
