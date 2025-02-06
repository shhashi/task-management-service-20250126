package shhashi.practice.i20250126.presentation.routes.login

import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*

fun Route.loginRoutes() {
    get("/login") {
        call.respond(ThymeleafContent("built/login/index", emptyMap()))
    }

    authenticate("auth-login") {
        post("/login") {
            val principal = call.principal<UserIdPrincipal>()
            call.respondText("Welcome ${principal?.name}")
        }
    }
}
