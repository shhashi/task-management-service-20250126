package shhashi.practice.i20250126.presentation.routes.api.account.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get
import shhashi.practice.i20250126.core.auth.JwtAuthentication

fun Application.registerApiRoutes() {
    val jwtAuthentication: JwtAuthentication = get()

    routing {
        post("/api/account/register/{registerCode}") {
            val receive = call.receiveParameters()
            println(receive["accountId"])
            println(receive["password"])
            println(receive["passwordConfirmation"])

            // JWT 発行
            val jwt = jwtAuthentication.createJwt(UserIdPrincipal(name = "test"))

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
