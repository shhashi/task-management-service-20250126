package shhashi.practice.i20250126.presentation.routes.web

import io.ktor.server.application.*
import io.ktor.server.routing.*
import shhashi.practice.i20250126.presentation.routes.web.account.register.registerWebRoutes
import shhashi.practice.i20250126.presentation.routes.web.login.loginWebRoutes
import shhashi.practice.i20250126.presentation.routes.web.top.topWebRoutes

fun Application.webRoutes() {
    routing {
        topWebRoutes()
        loginWebRoutes()
        registerWebRoutes()
    }
}
