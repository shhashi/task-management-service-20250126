package shhashi.practice.i20250126.infrastructure

import org.jetbrains.exposed.sql.insertReturning
import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.entity.Project
import shhashi.practice.i20250126.infrastructure.tables.Projects
import java.time.OffsetDateTime

@Single
class ProjectsRepository {
    fun create(project: Project): Project {
        return loggedTransaction {
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
}
