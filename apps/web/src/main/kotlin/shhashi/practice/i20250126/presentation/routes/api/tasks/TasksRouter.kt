package shhashi.practice.i20250126.presentation.routes.api.tasks

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
import shhashi.practice.i20250126.core.tasks.TasksCreation
import java.time.OffsetDateTime

fun Application.tasksApiRoutes() {
    val tasksCreation: TasksCreation = get()

    routing {
        authenticate("auth-jwt") {
            put("/api/tasks") {
                val principal = call.principal<JWTPrincipal>()
                val accountId =
                    principal!!.payload.claims["accountId"]!!.asString().toInt() // TODO validation とエラーハンドリングを入れる。

                val request = call.receive<TaskCreationRequest>()
                val createdTask = tasksCreation.create(
                    taskName = request.taskName,
                    description = request.description,
                    finishDate = OffsetDateTime.parse(request.finishDate),
                    accountId = accountId,
                    projectId = request.projectId,
                    statusId = 3 // TODO デフォルトのステータス ID をハードコーディングしている問題
                )

                call.respond(HttpStatusCode.OK, mapOf("taskId" to createdTask.first, "taskName" to createdTask.second))
            }
        }
    }
}

@Serializable
data class TaskCreationRequest(
    @SerialName("taskName") val taskName: String,
    @SerialName("description") val description: String,
    @SerialName("finishDate") val finishDate: String,
    @SerialName("projectId") val projectId: String,
)
