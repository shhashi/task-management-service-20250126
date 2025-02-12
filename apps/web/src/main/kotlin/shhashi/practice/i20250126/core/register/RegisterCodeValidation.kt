package shhashi.practice.i20250126.core.register

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.RegistrationCodeRepository

@Single
class RegisterCodeValidation(private val registrationCodeRepository: RegistrationCodeRepository) {
    fun validate(registerCode: String): Boolean {
        val activeCodes = registrationCodeRepository.findActiveRegistrationCodeBy(registerCode)
        // 登録コードが正常である条件
        //  - Active な登録コードが一件のみ
        return !(activeCodes.isEmpty() || activeCodes.size != 1)
    }
}
