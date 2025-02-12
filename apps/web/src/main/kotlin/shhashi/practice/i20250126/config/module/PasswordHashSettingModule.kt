package shhashi.practice.i20250126.config.module

import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.dsl.module
import shhashi.practice.i20250126.config.settings.PasswordHashSettings

fun Application.passwordHashSetting(): Module {
    val secret = environment.config.property("hash.secret").getString()
    return module {
        single {
            PasswordHashSettings(
                secret = secret
            )
        }
    }
}
