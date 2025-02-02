package shhashi.practice.i20250126.presentation.auth

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureFormAuthentication() {
    install(Authentication) {
        form("auth-login") {
            userParamName = "accountId"
            passwordParamName = "password"
            validate { credentials ->
                if (credentials.name == "user" && credentials.password == "pass") {
                    UserIdPrincipal(credentials.name)
                } else null
            }
        }
    }
}
