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
import shhashi.practice.i20250126.core.tasks.TaskSearch
import shhashi.practice.i20250126.core.tasks.TasksCreation
import shhashi.practice.i20250126.core.tasks.status.StatusMapping
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun Application.tasksApiRoutes() {
    val tasksCreation: TasksCreation = get()
    val taskSearch: TaskSearch = get()
    val statusMapping: StatusMapping = get()

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

            get("/api/tasks") {
                val request = TaskSearchRequest(
                    assigned = call.parameters["assigned"]?.toIntOrNull(),
                    statusId = call.parameters["statusId"]?.toIntOrNull(),
                    projectId = call.parameters["projectId"],
                    page = call.parameters["page"]?.toIntOrNull(),
                )

                val tasks = taskSearch.search(
                    assignedAccountID = request.assigned,
                    projectId = request.projectId,
                    statusId = request.statusId,
                    page = request.page,
                )

                call.respond(
                    HttpStatusCode.OK,
                    mapOf("tasks" to tasks.map {
                        mapOf(
                            "taslId" to it.taskId,
                            "taskName" to it.taskName,
                            "statusId" to it.statusId.toString(),
                            "statusName" to statusMapping.map(it.statusId!!),
                            "projectId" to it.projectId,
                            "finishDate" to it.finishDate?.format(DateTimeFormatter.ISO_ZONED_DATE_TIME),
                            "assignedAccountID" to it.accountId.toString(),
                            "assignedAccountName" to it.accountName
                        )
                    })
                )
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

data class TaskSearchRequest(
    @SerialName("assigned") val assigned: Int?,
    @SerialName("statusId") val statusId: Int?,
    @SerialName("projectId") val projectId: String?,
    @SerialName("page") val page: Int?,
)
