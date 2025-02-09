package shhashi.practice.i20250126.presentation.routes.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import org.koin.ktor.ext.get
import shhashi.practice.i20250126.core.auth.JwtAuthentication

fun Application.loginRoutes() {
    val jwtAuthentication: JwtAuthentication = get()

    routing {
        get("/login") {
            call.respond(ThymeleafContent("built/login/index", emptyMap()))
        }

        authenticate("auth-login") {
            post("/login") {
                val principal = call.principal<UserIdPrincipal>()

                // JWT 発行
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
                    )
                )
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
