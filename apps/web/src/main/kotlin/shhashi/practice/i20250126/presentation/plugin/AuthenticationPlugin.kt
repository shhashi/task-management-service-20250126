package shhashi.practice.i20250126.presentation.plugin

import io.ktor.server.application.*
import io.ktor.server.auth.*
import org.koin.ktor.ext.get
import shhashi.practice.i20250126.config.settings.JwtSettings
import shhashi.practice.i20250126.core.auth.FormAuthentication
import shhashi.practice.i20250126.presentation.plugin.auth.formAuthentication
import shhashi.practice.i20250126.presentation.plugin.auth.jwtAuthentication

fun Application.authentication() {
    val jwtSettings: JwtSettings = get()
    val formAuthentication: FormAuthentication = get()

    install(Authentication) {
        formAuthentication(formAuthentication)
        jwtAuthentication(jwtSettings)
    }
}
