package shhashi.practice.i20250126.core.register

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.infrastructure.RegistrationCodesRepository

@Single
class RegisterCodeValidation(private val registrationCodesRepository: RegistrationCodesRepository) {
    fun validate(registerCode: String): Boolean {
        val activeCodes = registrationCodesRepository.findActiveRegistrationCodeBy(registerCode)
        // 登録コードが正常である条件
        //  - Active な登録コードが一件のみ
        return !(activeCodes.isEmpty() || activeCodes.size != 1)
    }
}
