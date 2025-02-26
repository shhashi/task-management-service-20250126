package shhashi.practice.i20250126.core.tasks

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.repository.TasksRepository
import shhashi.practice.i20250126.infrastructure.repository.entity.Task
import java.time.OffsetDateTime

@Single
class TasksCreation(private val tasksRepository: TasksRepository) {
    fun create(
        taskName: String,
        description: String? = null,
        finishDate: OffsetDateTime? = null,
        accountId: Int? = null,
        projectId: String? = null,
        statusId: Int? = null,
    ): Pair<String?, String?> {
        val createdTask = tasksRepository.create(
            Task(
                taskName = taskName,
                description = description,
                finishDate = finishDate,
                accountId = accountId,
                projectId = projectId,
                statusId = statusId,
            )
        )
        return createdTask.taskId to createdTask.taskName
    }
}
