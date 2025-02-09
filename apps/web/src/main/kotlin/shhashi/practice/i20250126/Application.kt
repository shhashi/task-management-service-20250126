package shhashi.practice.i20250126

import io.ktor.server.application.*
import io.ktor.server.thymeleaf.*
import org.koin.ksp.generated.module
import org.koin.ktor.plugin.Koin
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import shhashi.practice.i20250126.presentation.di.KoinAnnotationModule
import shhashi.practice.i20250126.presentation.di.jwtConfig
import shhashi.practice.i20250126.presentation.plugin.auth.formAuthentication
import shhashi.practice.i20250126.presentation.routes.login.loginRoutes
import shhashi.practice.i20250126.presentation.routes.staticRouting
import shhashi.practice.i20250126.presentation.routes.top.topRoutes

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    // Koin
    install(Koin) {
        modules(KoinAnnotationModule().module, jwtConfig())
    }

    // Thymelead
    val templateResolver = ClassLoaderTemplateResolver().apply {
        prefix = "/template/"
        suffix = ".html"
        characterEncoding = "UTF-8"
    }

    install(Thymeleaf) {
        setTemplateResolver(templateResolver)
    }

    // Authentication
    formAuthentication()

    // Routing
    staticRouting()
    loginRoutes()
    topRoutes()
}
