package shhashi.practice.i20250126.presentation.routes.web.top

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*

fun Application.topWebRoutes() {
    routing {
        authenticate("auth-jwt") {
            get("/") {
                call.respond(ThymeleafContent("built/top/service/index", emptyMap()))
            }
        }
    }
}
