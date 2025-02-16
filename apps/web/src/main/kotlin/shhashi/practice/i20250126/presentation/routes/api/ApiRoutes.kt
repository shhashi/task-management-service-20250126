package shhashi.practice.i20250126.presentation.routes.api

import io.ktor.server.application.*
import io.ktor.server.routing.*
import shhashi.practice.i20250126.presentation.routes.api.account.register.registerApiRoutes
import shhashi.practice.i20250126.presentation.routes.api.login.loginApiRoutes
import shhashi.practice.i20250126.presentation.routes.api.projects.projectsApiRoutes

fun Application.apiRoutes() {
    routing {
        loginApiRoutes()
        registerApiRoutes()
        projectsApiRoutes()
    }
}
