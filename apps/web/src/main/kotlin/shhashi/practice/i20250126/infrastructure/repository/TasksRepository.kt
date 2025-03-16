package shhashi.practice.i20250126.infrastructure.repository

import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.insertReturning
import org.jetbrains.exposed.sql.leftJoin
import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.dao.tables.Accounts
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

    fun count(
        assignedAccountID: Int? = null,
        statusId: Int? = null,
        projectId: String? = null
    ): Long {
        return loggedTransaction {
            Tasks.select(Tasks.taskId).apply {
                if (assignedAccountID != null) {
                    where { Tasks.accountId eq assignedAccountID }
                }
                if (statusId != null) {
                    andWhere { Tasks.statusId eq statusId }
                }
                if (projectId != null) {
                    andWhere { Tasks.projectId eq projectId }
                }
            }.count()
        }
    }

    fun search(
        assignedAccountID: Int? = null,
        statusId: Int? = null,
        projectId: String? = null,
        limit: Int? = null,
        offset: Long? = null,
    ): List<Task> {
        return loggedTransaction {
            Tasks
                .leftJoin(Accounts, { accountId }, { accountId })
                .select(
                    Tasks.taskId,
                    Tasks.taskName,
                    Tasks.statusId,
                    Tasks.projectId,
                    Tasks.finishDate,
                    Tasks.accountId,
                    Accounts.accountName
                )
                .apply {
                    if (assignedAccountID != null) {
                        where { Tasks.accountId eq assignedAccountID }
                    }
                    if (statusId != null) {
                        andWhere { Tasks.statusId eq statusId }
                    }
                    if (projectId != null) {
                        andWhere { Tasks.projectId eq projectId }
                    }
                }
                .limit(limit!!)
                .offset(offset!!)
                .orderBy(Tasks.finishDate, SortOrder.ASC)
                .map {
                    Task(
                        taskId = it[Tasks.taskId],
                        taskName = it[Tasks.taskName],
                        statusId = it[Tasks.statusId],
                        projectId = it[Tasks.projectId],
                        finishDate = it[Tasks.finishDate],
                        accountId = it[Tasks.accountId],
                        accountName = it[Accounts.accountName]
                    )
                }
        }
    }
}
