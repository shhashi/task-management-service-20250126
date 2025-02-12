package shhashi.practice.i20250126.presentation.di

import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.dsl.module
import shhashi.practice.i20250126.presentation.di.config.JwtConfiguration

fun Application.jwtConfig(): Module {
    val secret = environment.config.property("jwt.secret").getString()
    return module {
        single {
            JwtConfiguration(
                secret = secret
            )
        }
    }
}
