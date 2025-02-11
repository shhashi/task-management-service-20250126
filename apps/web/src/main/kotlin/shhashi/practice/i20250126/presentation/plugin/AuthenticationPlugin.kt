package shhashi.practice.i20250126.presentation.plugin

import io.ktor.server.application.*
import io.ktor.server.auth.*
import org.koin.ktor.ext.get
import shhashi.practice.i20250126.presentation.di.config.JwtConfiguration
import shhashi.practice.i20250126.presentation.plugin.auth.formAuthentication
import shhashi.practice.i20250126.presentation.plugin.auth.jwtAuthentication

fun Application.authentication() {
    val jwtConfiguration: JwtConfiguration = get()

    install(Authentication) {
        formAuthentication()
        jwtAuthentication(jwtConfiguration)
    }
}
