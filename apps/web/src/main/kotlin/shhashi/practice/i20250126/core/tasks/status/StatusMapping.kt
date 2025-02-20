package shhashi.practice.i20250126.core.tasks.status

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.repository.StatusRepository

@Single
class StatusMapping(statusRepository: StatusRepository) {

    private var statusMap: Map<Int, String> // TODO これだとアプリケーション起動後から更新されないため、これでいいか再確認。

    init {
        val statusList = statusRepository.find()
        statusMap = statusList.associate { it.statusId!! to it.statusName!! } // TODO アサーションを入れる
    }

    fun maps(vararg statusIds: Int): List<String?> {
        return statusIds.map { this.map(it) }
    }

    fun map(statusId: Int): String? {
        return statusMap[statusId]
    }
}
