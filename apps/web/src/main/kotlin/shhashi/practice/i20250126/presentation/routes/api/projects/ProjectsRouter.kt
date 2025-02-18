package shhashi.practice.i20250126.presentation.routes.api.projects

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.get
import shhashi.practice.i20250126.core.projects.ProjectCreation

fun Application.projectsApiRoutes() {
    val projectCreation: ProjectCreation = get()

    routing {
        authenticate("auth-jwt") {
            put("/api/projects") {
                val request = call.receive<ProjectCreationRequest>()
                val principal = call.principal<JWTPrincipal>()

                val createdProjectIdToName = projectCreation.create(
                    request.projectId,
                    request.projectName,
                    principal!!.payload.claims["accountId"]!!.asString().toInt() // TODO validation とエラーハンドリングを入れる。
                )
                call.respond(
                    HttpStatusCode.OK,
                    mapOf("projectId" to createdProjectIdToName.first, "projectName" to createdProjectIdToName.second)
                )
            }
        }
    }
}

@Serializable
data class ProjectCreationRequest(
    @SerialName("projectId") val projectId: String,
    @SerialName("projectName") val projectName: String
)
