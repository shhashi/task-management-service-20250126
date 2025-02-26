package shhashi.practice.i20250126.infrastructure.repository

import org.jetbrains.exposed.sql.insertReturning
import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.dao.tables.Tasks
import shhashi.practice.i20250126.infrastructure.loggedTransaction
import shhashi.practice.i20250126.infrastructure.repository.entity.Task
import java.time.OffsetDateTime

@Single
class TasksRepository {
    fun create(task: Task): Task {
        return loggedTransaction {
            // タスク ID 用のシーケンスから値を取得
            val sequenceId = exec("SELECT nextval('task_id_sequence_${task.projectId}')") { rs ->
                if (rs.next()) rs.getLong(1) else null
            }
            // タスクを登録
            val result = Tasks.insertReturning {
                it[taskId] = task.projectId!! + "-" + String.format("%05d", sequenceId)
                it[taskName] = task.taskName!!
                it[description] = task.description
                it[finishDate] = task.finishDate!!
                it[accountId] = task.accountId!!
                it[projectId] = task.projectId
                it[statusId] = task.statusId!!
                it[createdAt] = OffsetDateTime.now()
            }.single()
            return@loggedTransaction Task(
                taskId = result[Tasks.taskId],
                taskName = result[Tasks.taskName],
            )
        }
    }
}
