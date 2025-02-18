package shhashi.practice.i20250126.infrastructure.dao

import org.jetbrains.exposed.sql.insertReturning
import shhashi.practice.i20250126.infrastructure.dao.entity.Project
import shhashi.practice.i20250126.infrastructure.dao.tables.Projects
import java.time.OffsetDateTime

object ProjectsDao {
    val create: (project: Project) -> Project = { project ->
        val result = Projects.insertReturning {
            it[projectId] = project.projectId!!
            it[projectName] = project.projectName!!
            it[createdAt] = OffsetDateTime.now()
        }.single()
        Project(
            projectId = result[Projects.projectId],
            projectName = result[Projects.projectName],
        )
    }
}
