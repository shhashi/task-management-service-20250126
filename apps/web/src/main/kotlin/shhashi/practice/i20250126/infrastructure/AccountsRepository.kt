package shhashi.practice.i20250126.infrastructure

import org.jetbrains.exposed.sql.insert
import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.entity.Account
import shhashi.practice.i20250126.infrastructure.tables.Accounts
import java.time.OffsetDateTime

@Single
class AccountsRepository {
    fun create(account: Account): Int {
        return loggedTransaction {
            Accounts.insert {
                it[loginId] = account.loginId!!
                it[accountName] = account.accountName!!
                it[password] = account.password!!
                it[createdAt] = OffsetDateTime.now()
            } get Accounts.accountId
        }
    }
}
