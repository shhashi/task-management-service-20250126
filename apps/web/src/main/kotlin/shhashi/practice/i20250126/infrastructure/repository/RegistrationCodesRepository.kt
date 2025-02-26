package shhashi.practice.i20250126.infrastructure.repository

import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.update
import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.dao.tables.RegistrationCodes
import shhashi.practice.i20250126.infrastructure.loggedTransaction
import shhashi.practice.i20250126.infrastructure.repository.entity.RegistrationCode
import java.time.OffsetDateTime

@Single
class RegistrationCodesRepository {
    fun findActiveRegistrationCodeBy(registrationCode: String): List<RegistrationCode> { // TODO Daoにくくりだしたい
        return loggedTransaction {
            RegistrationCodes
                .select(
                    RegistrationCodes.registrationCodeId,
                    RegistrationCodes.registrationCode,
                    RegistrationCodes.expiredIn
                )
                .where {
                    RegistrationCodes.registrationCode eq registrationCode
                }
                .andWhere {
                    RegistrationCodes.expiredIn greaterEq OffsetDateTime.now()
                }
                .andWhere {
                    RegistrationCodes.accountId.isNull()
                }.map {
                    RegistrationCode(
                        registrationCodeId = it[RegistrationCodes.registrationCodeId],
                        registrationCode = it[RegistrationCodes.registrationCode],
                        expiredIn = it[RegistrationCodes.expiredIn],
                    )
                }
        }
    }

    fun updateCompletedRegistrationCode(registrationCode: String, accountId: Int) {
        return loggedTransaction {
            RegistrationCodes.update(where = { RegistrationCodes.registrationCode eq registrationCode }) {
                it[RegistrationCodes.accountId] = accountId
            }
        }
    }
}
