package shhashi.practice.i20250126.core.projects

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.repository.ProjectRepository

@Single
class ProjectsRetrieval(
    private val projectRepository: ProjectRepository
) {
    fun findByAccountId(accountId: Int): List<Pair<String, String>> {
        val projects = projectRepository.fetchProjectsByAccountId(accountId)
        return projects.map {
            it.projectId!! to it.projectName!!
        }
    }
}
