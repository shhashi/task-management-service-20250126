package shhashi.practice.i20250126.core.register

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.config.settings.PasswordHashSettings
import shhashi.practice.i20250126.infrastructure.repository.AccountsRepository
import shhashi.practice.i20250126.infrastructure.repository.entity.Account
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

@Single
class AccountRegistration(
    private val passwordHashSettings: PasswordHashSettings,
    private val accountsRepository: AccountsRepository
) {
    /**
     * @return アカウント ID
     */
    fun register(accountId: String, password: String, passwordConfirmation: String, accountName: String): Int {
        // パスワードが一致していることを確認する
        if (password != passwordConfirmation) {
            throw IllegalArgumentException("Passwords do not match")
        }

        // TODO インスタンス化の部分は共通部分でシングルトンで定義したい
        // パスワードのハッシュ化
        val hashInstance = Mac.getInstance("HmacSHA256")
        hashInstance.apply {
            init(SecretKeySpec(passwordHashSettings.secret.toByteArray(Charsets.UTF_8), "HmacSHA256"))
        }

        val hashedPassword = hashInstance.doFinal(
            password.toByteArray(Charsets.UTF_8)
        ).joinToString("") { String.format("%02x", it and 255.toByte()) }

        val account = Account(loginId = accountId, accountName = accountName, password = hashedPassword)
        return accountsRepository.create(account)
    }
}
