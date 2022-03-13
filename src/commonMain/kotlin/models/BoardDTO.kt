package models

import kotlinx.serialization.Serializable

/**
 * This class is used to transfer only necessary info to client.
 * @property Array<Array<Int?>> gameboard.
 * @property Int? if there is no winner so far - null, else a winner.
 * @property MutableSet<models.Point> if there is a winner, then sequence which led to a win.
 * @constructor Creates a dummy models.BoardDTO.
 */

@Serializable
data class BoardDTO(
    var boardArray: Array<Array<Int?>> = arrayOf<Array<Int?>>(),
    var winner: Int? = null,
    var winSquares: MutableSet<Point> = mutableSetOf(),
)