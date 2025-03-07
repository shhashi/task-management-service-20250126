package shhashi.practice.i20250126.infrastructure.dao

import org.jetbrains.exposed.sql.insert
import shhashi.practice.i20250126.infrastructure.dao.tables.AccountRoles
import shhashi.practice.i20250126.infrastructure.repository.entity.AccountRole
import java.time.OffsetDateTime

object AccountRolesDao {
    val create: (accountRole: AccountRole) -> Unit = { accountRole ->
        AccountRoles.insert {
            it[accountId] = accountRole.accountId!!
            it[projectId] = accountRole.projectId!!
            it[roleId] = accountRole.roleId!!
            it[createdAt] = OffsetDateTime.now()
        }
    }
}
