package shhashi.practice.i20250126.infrastructure

import org.jetbrains.exposed.sql.andWhere
import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.entity.RegistrationCode
import shhashi.practice.i20250126.infrastructure.tables.RegistrationCodes
import java.time.OffsetDateTime

@Single
class RegistrationCodesRepository {
    fun findActiveRegistrationCodeBy(registrationCode: String): List<RegistrationCode> {
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
                }.map { it[RegistrationCodes.registrationCode]
                    RegistrationCode(
                        registrationCodeId = it[RegistrationCodes.registrationCodeId],
                        registrationCode = it[RegistrationCodes.registrationCode],
                        expiredIn = it[RegistrationCodes.expiredIn],
                    )
                }
        }
    }
}
