package shhashi.practice.i20250126.presentation.routes.web.login

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*

fun Application.loginWebRoutes() {
    routing {
        get("/login") {
            // TODO すでにトークンを保持している場合は警告画面に遷移させるようにする。
            call.respond(ThymeleafContent("built/login/index", emptyMap()))
        }
    }
}
