package shhashi.practice.i20250126.infrastructure.repository

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.dao.tables.Status
import shhashi.practice.i20250126.infrastructure.loggedTransaction
import shhashi.practice.i20250126.infrastructure.repository.entity.State

@Single
class StatusRepository {
    fun find(): List<State> {
        return loggedTransaction {
            Status
                .select(Status.statusId, Status.statusName)
                .map {
                    State(
                        statusId = it[Status.statusId],
                        statusName = it[Status.statusName],
                    )
                }
        }
    }
}
