package shhashi.practice.i20250126.config.module

import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.dsl.module
import shhashi.practice.i20250126.config.settings.JwtSettings

fun Application.jwtSetting(): Module {
    val secret = environment.config.property("jwt.secret").getString()
    return module {
        single {
            JwtSettings(
                secret = secret
            )
        }
    }
}
