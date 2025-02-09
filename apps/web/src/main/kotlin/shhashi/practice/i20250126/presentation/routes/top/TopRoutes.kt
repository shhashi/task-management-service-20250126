package shhashi.practice.i20250126.presentation.routes.top

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*

fun Application.topRoutes() {
    routing {
        get("/") {
            call.respond(ThymeleafContent("built/top/index", emptyMap()))
        }
    }
}
