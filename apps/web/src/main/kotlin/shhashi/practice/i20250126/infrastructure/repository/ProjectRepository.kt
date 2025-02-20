package shhashi.practice.i20250126.infrastructure.repository

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.dao.ProjectsDao
import shhashi.practice.i20250126.infrastructure.dao.RegistrationCodesDao
import shhashi.practice.i20250126.infrastructure.repository.entity.AccountRole
import shhashi.practice.i20250126.infrastructure.repository.entity.Project
import shhashi.practice.i20250126.infrastructure.loggedTransaction

@Single
class ProjectRepository {
    fun createProject(project: Project, createdByAccountId: Int): Project {
        return loggedTransaction {
            // プロジェクトの作成
            val createdProject = ProjectsDao.create(project)
            // プロジェクト作成者を紐づけ
            val accountRole = AccountRole(
                accountId = createdByAccountId,
                projectId = createdProject.projectId,
                roleId = "ADMIN" // TODO ここ固定値でいいのか再検討
            )
            RegistrationCodesDao.create(accountRole)
            createdProject
        }
    }

    fun findByAccountId(accountId: Int): List<Project> {
        return loggedTransaction {
            ProjectsDao.selectByAccountId(accountId)
        }
    }
}
