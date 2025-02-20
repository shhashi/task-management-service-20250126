package shhashi.practice.i20250126.infrastructure.dao.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestampWithTimeZone

object Status : Table("status") {
    val statusId = integer("status_id").autoIncrement()
    val statusName = varchar("status_name", 100)
    val createdAt = timestampWithTimeZone("created_at").nullable()
    val deletedAt = timestampWithTimeZone("deleted_at").nullable()
}
