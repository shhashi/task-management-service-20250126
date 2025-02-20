package shhashi.practice.i20250126.infrastructure.repository

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.dao.tables.Status
import shhashi.practice.i20250126.infrastructure.loggedTransaction

@Single
class StatusRepository {
    fun find(): List<shhashi.practice.i20250126.infrastructure.repository.entity.Status> {
        return loggedTransaction {
            Status
                .select(Status.statusId, Status.statusName)
                .map {
                    shhashi.practice.i20250126.infrastructure.repository.entity.Status(
                        statusId = it[Status.statusId],
                        statusName = it[Status.statusName],
                    )
                }
        }
    }
}
