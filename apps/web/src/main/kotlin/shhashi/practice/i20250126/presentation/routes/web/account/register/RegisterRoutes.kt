package shhashi.practice.i20250126.presentation.routes.web.account.register

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import org.koin.ktor.ext.get
import shhashi.practice.i20250126.core.register.RegisterCodeValidation

fun Application.registerWebRoutes() {
    val registerCodeValidation: RegisterCodeValidation = get()

    routing {
        get("/account/register/{registerCode}") {
            val isActiveRegisterCode = registerCodeValidation.validate(call.parameters["registerCode"] as String)
            val mapper = jacksonObjectMapper() // TODO 共通変数としてインスタンス化を省略できるようにする

            if (isActiveRegisterCode) {
                call.respond(
                    ThymeleafContent(
                        "built/account/register/index",
                        mapOf("props" to mapper.writeValueAsString(mapOf("registerCode" to call.parameters["registerCode"]!!)))
                    )
                )
            } else {
                call.respond(
                    ThymeleafContent(
                        "built/error/index",
                        mapOf("props" to mapper.writeValueAsString(mapOf("errorMessage" to "この URL では登録できません。管理者に確認してください。")))
                    )
                )
            }
        }
    }
}
