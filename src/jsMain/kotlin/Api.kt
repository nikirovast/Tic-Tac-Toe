import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

import kotlinx.browser.window
import models.Board
import models.BoardDTO

val endpoint = window.location.origin // only needed until https://youtrack.jetbrains.com/issue/KTOR-453 is resolved

val jsonClient = HttpClient {
    install(JsonFeature) { serializer = KotlinxSerializer() }
}

/**
 * Client endpoints to access backend
 */

suspend fun getBoard(): BoardDTO {
    return jsonClient.get(endpoint + Board.path)
}

suspend fun resetBoard() {
    jsonClient.put<Unit>(endpoint + Board.path)
}

suspend fun updateSquare(id: Int) {
    jsonClient.put<Unit>(endpoint + Board.path + "/${id}")
}
