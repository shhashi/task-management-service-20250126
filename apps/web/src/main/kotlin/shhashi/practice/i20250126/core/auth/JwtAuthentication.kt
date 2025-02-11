package shhashi.practice.i20250126.core.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.*
import org.koin.core.annotation.Single
import shhashi.practice.i20250126.presentation.di.config.JwtConfiguration
import java.util.*

@Single
class JwtAuthentication(private val jwtConfiguration: JwtConfiguration) {
    fun createJwt(account: UserIdPrincipal?): String {
        if (account == null) {
            throw IllegalStateException("`account` is null")
        }

        return JWT.create()
            .withClaim("accountId", account.name)
            .withExpiresAt(Date(System.currentTimeMillis() + 360_000_000)) // TODO 有効期間はアプリの設定ファイルから設定できるようにしたい
            .sign(Algorithm.HMAC256(jwtConfiguration.secret))
    }
}
