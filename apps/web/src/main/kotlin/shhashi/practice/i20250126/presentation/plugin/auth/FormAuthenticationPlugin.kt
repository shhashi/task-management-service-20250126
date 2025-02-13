package shhashi.practice.i20250126.presentation.plugin.auth

import io.ktor.server.auth.*
import shhashi.practice.i20250126.core.auth.FormAuthentication

fun AuthenticationConfig.formAuthentication(formAuthentication: FormAuthentication) {
    form("auth-login") {
        userParamName = "accountId"
        passwordParamName = "password"
        // ログイン認証
        validate { credentials ->
            val accountId = formAuthentication.validate(credentials.name, credentials.password)
            if (accountId != null) {
                UserIdPrincipal(accountId.toString())
            } else null
        }
    }
}
