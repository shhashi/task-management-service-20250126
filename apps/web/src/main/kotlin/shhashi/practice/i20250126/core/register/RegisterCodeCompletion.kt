package shhashi.practice.i20250126.core.register

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.repository.RegistrationCodesRepository

@Single
class RegisterCodeCompletion(private val registrationCodesRepository: RegistrationCodesRepository) {
    fun complete(registrationCode: String, accountId: Int) {
        registrationCodesRepository.updateCompletedRegistrationCode(registrationCode, accountId)
    }
}
