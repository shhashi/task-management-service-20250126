package shhashi.practice.i20250126.presentation.routes

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import shhashi.practice.i20250126.presentation.routes.login.LoginRoutes.Companion.loginRoutes

fun Application.configureRouting() {
    routing {
        staticResources("/static", "static")
        staticResources("/assets", "static/assets")

        loginRoutes()
    }
}
