import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.http.content.*
import kotlinx.html.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.serialization.*
import models.Board
import kotlin.math.*

var gameBoard: Board = Board()

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Put)
            method(HttpMethod.Post)
            method(HttpMethod.Delete)
            anyHost()
        }
        routing {
            get("/") {
                call.respondText(
                    this::class.java.classLoader.getResource("index.html")!!.readText(),
                    ContentType.Text.Html
                )
            }
            static("/static") {
                resources()
            }
            route(Board.path) {
                get {
                    call.respond(gameBoard.getDTO())
                }
                put {
                    resetBoard()
                    call.respond(HttpStatusCode.OK)
                }
                put("/{id}") {
                    val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
                    val res = performClientStep(gameBoard, id)
                    if (res == -1) {
                        call.respond(HttpStatusCode.BadRequest)
                    }
                    call.respond(HttpStatusCode.OK)
                }
            }
        }
    }.start(wait = true)
}


