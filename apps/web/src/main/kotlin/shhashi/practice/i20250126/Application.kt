package shhashi.practice.i20250126

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.thymeleaf.*
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        port = 8080,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    val templateResolver = ClassLoaderTemplateResolver().apply {
        prefix = "/template/"
        suffix = ".html"
        characterEncoding = "UTF-8"
    }

    install(Thymeleaf) {
        setTemplateResolver(templateResolver)
    }
}
