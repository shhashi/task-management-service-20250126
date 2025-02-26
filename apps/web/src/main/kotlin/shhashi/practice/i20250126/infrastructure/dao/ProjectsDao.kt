package shhashi.practice.i20250126.infrastructure.dao

import org.jetbrains.exposed.sql.insertReturning
import shhashi.practice.i20250126.infrastructure.dao.tables.AccountRoles
import shhashi.practice.i20250126.infrastructure.dao.tables.Projects
import shhashi.practice.i20250126.infrastructure.repository.entity.Project
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

    val selectByAccountId: (accountId: Int) -> List<Project> = { accountId ->
        (Projects innerJoin AccountRoles)
            .select(Projects.projectId, Projects.projectName)
            .where { AccountRoles.accountId eq accountId }
            .map {
                Project(
                    projectId = it[Projects.projectId],
                    projectName = it[Projects.projectName],
                )
            }
    }
}
