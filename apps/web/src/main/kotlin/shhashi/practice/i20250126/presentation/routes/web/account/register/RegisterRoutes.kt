package shhashi.practice.i20250126.presentation.routes.account.register

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import org.koin.ktor.ext.get
import shhashi.practice.i20250126.core.auth.JwtAuthentication
import shhashi.practice.i20250126.core.register.RegisterCodeValidation

fun Application.registerRoutes() {
    val jwtAuthentication: JwtAuthentication = get()
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
                        emptyMap()
                    )
                )
            }
        }

        post("/account/register/{registerCode}") {
            val receive = call.receiveParameters()
            println(receive["accountId"])
            println(receive["password"])
            println(receive["passwordConfirmation"])

            // JWT 発行
            val jwt = jwtAuthentication.createJwt(UserIdPrincipal(name = "test"))

            // JWT を cookie に設定
            call.response.cookies.append(
                "task-management-session",
                jwt,
                maxAge = 100000,
                httpOnly = true,
                secure = false,
                extensions = mapOf(
                    "SameSite" to "Lax"
                ),
                path = "/"
            )
            call.respond(HttpStatusCode.OK)
        }
    }
}
