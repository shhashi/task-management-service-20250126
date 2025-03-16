package shhashi.practice.i20250126

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.thymeleaf.*
import org.koin.ksp.generated.module
import org.koin.ktor.ext.get
import org.koin.ktor.plugin.Koin
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import shhashi.practice.i20250126.config.module.KoinAnnotationModule
import shhashi.practice.i20250126.config.module.jwtSetting
import shhashi.practice.i20250126.config.module.passwordHashSetting
import shhashi.practice.i20250126.config.settings.ExposedSettings
import shhashi.practice.i20250126.infrastructure.repository.RegistrationCodesRepository
import shhashi.practice.i20250126.presentation.plugin.authentication
import shhashi.practice.i20250126.presentation.routes.api.apiRoutes
import shhashi.practice.i20250126.presentation.routes.staticRouting
import shhashi.practice.i20250126.presentation.routes.web.webRoutes

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    // Koin
    install(Koin) {
        modules(KoinAnnotationModule().module, jwtSetting(), passwordHashSetting())
    }

    // Exposed
    ExposedSettings.initDb()

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
    authentication()

    // Routing
    staticRouting()
    webRoutes()
    apiRoutes()

    // 起動時の処理 TODO どこかにくくりだしたい
    // - 最初のアカウントの登録コードを表示
    val registrationCodesRepository: RegistrationCodesRepository = get()
    val firstRegistrationCode = registrationCodesRepository.findAll()[0].registrationCode
    println("----------")
    println("check code: $firstRegistrationCode")
    println("----------")
}
