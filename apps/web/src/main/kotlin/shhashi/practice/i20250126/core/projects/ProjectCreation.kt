package shhashi.practice.i20250126.core.projects

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.dao.entity.Project
import shhashi.practice.i20250126.infrastructure.repository.ProjectRepository

@Single
class ProjectCreation(
    private val projectRepository: ProjectRepository
) {

    fun create(projectId: String, projectName: String, createdByAccountId: Int): Pair<String?, String?> {
        val project = Project(
            projectId = projectId,
            projectName = projectName,
        )
        val insertedProject = projectRepository.createProject(project, createdByAccountId)
        return insertedProject.projectId to insertedProject.projectName
    }
}
