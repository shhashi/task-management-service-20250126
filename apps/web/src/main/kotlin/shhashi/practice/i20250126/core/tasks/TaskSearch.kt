package shhashi.practice.i20250126.core.tasks

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.repository.TasksRepository
import shhashi.practice.i20250126.infrastructure.repository.entity.Task

@Single
class TaskSearch(private val tasksRepository: TasksRepository) {
    fun search(
        assignedAccountID: Int? = null,
        statusId: Int? = null,
        projectId: String? = null,
        page: Int? = null,
    ): List<Task> { // プロトタイプのためこうしているが、 Entity を presentation に渡したくない
        val pageSize = 100 // 将来的に可変にする。
        val offsetSize = ((page ?: 1) - 1).toLong() * pageSize

        return tasksRepository.search(
            assignedAccountID = assignedAccountID,
            statusId = statusId,
            projectId = projectId,
            limit = pageSize,
            offset = offsetSize
        )
    }
}
