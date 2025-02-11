package shhashi.practice.i20250126.presentation.plugin.auth

import io.ktor.server.auth.*

fun AuthenticationConfig.formAuthentication() {
    form("auth-login") {
        userParamName = "accountId"
        passwordParamName = "password"
        // ログイン認証
        validate { credentials ->
            if (credentials.name == "user" && credentials.password == "pass") {
                UserIdPrincipal(credentials.name)
            } else null
        }
    }
}
