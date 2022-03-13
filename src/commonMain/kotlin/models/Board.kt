package models

import kotlinx.serialization.Serializable

/**
 * This class is used to store game info.
 * @param Int number of cells in the board.
 * @param Int number of cells needed to win.
 * @property Array<Array<Int?>> gameboard.
 * @property Int? if there is no winner so far - null, else a winner.
 * @property MutableSet<models.Point> if there is a winner, then sequence which led to a win.
 * @property MutableSet<models.Point> possible computer steps at this stage of the game.
 * @constructor Creates a dummy models.BoardDTO.
 */

@Serializable
data class Board(val numInRow: Int = 10, val numToWin: Int = 5) {
    var boardArray = Array<Array<Int?>>(numInRow) { Array<Int?>(numInRow) { null } }
    var winner: Int? = null
    var winSquares: MutableSet<Point> = mutableSetOf()
    var possibleSteps: MutableSet<Point> = mutableSetOf()

    /**
     * This function creates a copy of this board.
     * @return new object Board - copy of this.
     */

    fun copyBoard(): Board {
        val newBoard: Board = Board().apply {
            boardArray = Array<Array<Int?>>(numInRow) {
                Array<Int?>(numInRow) { null }
            }
            winSquares = this.winSquares.toMutableSet()
            winner = this.winner
            possibleSteps = this.possibleSteps.toMutableSet()
        }
        //copy array
        val n: Int = this.boardArray.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - 1) {
                newBoard.boardArray[i][j] = this.boardArray[i][j]
            }
        }
        return newBoard
    }

    /**
     * This function creates a DTO for current board state.
     * @return BoardDTO.
     */

    fun getDTO(): BoardDTO {
        return BoardDTO(this.boardArray, this.winner, this.winSquares)
    }

    companion object {
        const val path = "/board"
    }
}
