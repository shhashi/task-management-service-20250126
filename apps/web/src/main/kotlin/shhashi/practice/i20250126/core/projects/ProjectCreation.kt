package shhashi.practice.i20250126.core.projects

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.ProjectsRepository
import shhashi.practice.i20250126.infrastructure.entity.Project

@Single
class ProjectCreation(
    private val projectsRepository: ProjectsRepository
) {

    fun create(projectId: String, projectName: String): String {
        val project = Project(
            projectId = projectId,
            projectName = projectName,
        )
        return projectsRepository.create(project)
    }
}
