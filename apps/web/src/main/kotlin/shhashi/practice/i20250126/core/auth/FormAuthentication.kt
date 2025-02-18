package shhashi.practice.i20250126.core.auth

import org.koin.core.annotation.Single
import shhashi.practice.i20250126.config.settings.PasswordHashSettings
import shhashi.practice.i20250126.infrastructure.repository.AccountsRepository
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

@Single
class FormAuthentication(
    private val accountsRepository: AccountsRepository,
    private val passwordHashSettings: PasswordHashSettings,
) {
    fun validate(loginId: String, password: String): Int? {
        // loginId に紐づくアカウントを取得する。
        val accounts = accountsRepository.findByLoginId(loginId)
        if (accounts.isEmpty()) {
            throw IllegalStateException("not found accounts")
        }
        if (accounts.size != 1) {
            throw IllegalStateException("Too many accounts")
        }

        // パスワード検証
        // TODO インスタンス化の部分は共通部分でシングルトンで定義したい
        // パスワードのハッシュ化
        val hashInstance = Mac.getInstance("HmacSHA256")
        hashInstance.apply {
            init(SecretKeySpec(passwordHashSettings.secret.toByteArray(Charsets.UTF_8), "HmacSHA256"))
        }

        val hashedPassword = hashInstance.doFinal(
            password.toByteArray(Charsets.UTF_8)
        ).joinToString("") { String.format("%02x", it and 255.toByte()) }

        if (hashedPassword != accounts[0].password) {
            throw IllegalArgumentException("Passwords do not match")
        }

        // アカウント情報を返す
        return accounts[0].accountId
    }
}
