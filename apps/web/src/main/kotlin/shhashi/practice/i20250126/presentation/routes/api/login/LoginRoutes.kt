package shhashi.practice.i20250126.presentation.routes.api.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get
import shhashi.practice.i20250126.core.auth.JwtAuthentication

fun Application.loginApiRoutes() {
    val jwtAuthentication: JwtAuthentication = get()

    routing {
        authenticate("auth-login") {
            post("/api/login") {
                val principal = call.principal<UserIdPrincipal>()

                // JWT 発行
                // TODO account_id で  JWT トークンを作成するように実装する。
                val jwt = jwtAuthentication.createJwt(principal)

                // JWT を cookie に設定
                call.response.cookies.append(
                    "task-management-session",
                    jwt,
                    maxAge = 100000,
                    httpOnly = true,
                    secure = false,
                    extensions = mapOf(
                        "SameSite" to "Lax"
                    ),
                    path = "/"
                )
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
