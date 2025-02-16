package shhashi.practice.i20250126.infrastructure.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestampWithTimeZone

object Projects : Table("projects") {
    val projectId = varchar("project_id", 100)
    val projectName = varchar("project_name", 100)
    val createdAt = timestampWithTimeZone("created_at").nullable()
    val deletedAt = timestampWithTimeZone("deleted_at").nullable()
}
