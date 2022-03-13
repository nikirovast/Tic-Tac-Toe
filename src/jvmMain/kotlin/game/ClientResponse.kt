import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import models.Board
import models.Point
import kotlin.math.*

/**
 * Function to reset the board to the initial state.
 * @param Board the game board.
 */

fun resetBoard() {
    gameBoard = Board()
}

/**
 * Function to create point from the 1-dimensional id with the board of size n
 * @param Int point id.
 * @param Int board Size.
 * @return point:models.Point.
 */

fun fromIdToPoint(id: Int, n: Int): Point {
    val x: Int = id / n
    val y: Int = id - x * n
    return Point(x, y)
}

/**
 * Function to make the step.
 * @param Board board to make step on.
 * @param Point point where to step.
 * @param Int player: 1 if human, -1 if computer.
 * @return 0 if success, -1 if fail.
 */

fun doStep(board: Board, point: Point, player: Int): Int {
    val x: Int = point.x
    val y: Int = point.y
    // this cell is not empty
    if (board.boardArray[x][y] != null) {
        return -1
    }
    board.boardArray[x][y] = player
    board.possibleSteps.remove(point)
    addNeighbours(board, x, y)
    return 0
}

/**
 * Function to add the current point neighbours to the possible computer steps set.
 * @param Board board to make step on.
 * @param Int point.x.
 * @param Int point.y.
 */

fun addNeighbours(board: Board, x: Int, y: Int) {
    val n: Int = board.boardArray.size
    if (x > 0 && board.boardArray[x - 1][y] == null) {
        board.possibleSteps.add(models.Point(x - 1, y))
    }
    if (x < n - 1 && board.boardArray[x + 1][y] == null) {
        board.possibleSteps.add(models.Point(x + 1, y))
    }
    if (y > 0 && board.boardArray[x][y - 1] == null) {
        board.possibleSteps.add(models.Point(x, y - 1))
    }
    if (y < n - 1 && board.boardArray[x][y + 1] == null) {
        board.possibleSteps.add(models.Point(x, y + 1))
    }
    if (x < n - 1 && y < n - 1 && board.boardArray[x + 1][y + 1] == null) {
        board.possibleSteps.add(models.Point(x + 1, y + 1))
    }
    if (x < n - 1 && y > 0 && board.boardArray[x + 1][y - 1] == null) {
        board.possibleSteps.add(models.Point(x + 1, y - 1))
    }
    if (x > 0 && y < n - 1 && board.boardArray[x - 1][y + 1] == null) {
        board.possibleSteps.add(models.Point(x - 1, y + 1))
    }
    if (x > 0 && y > 0 && board.boardArray[x - 1][y - 1] == null) {
        board.possibleSteps.add(models.Point(x - 1, y - 1))
    }
}

/**
 * Function to proceed client put request.
 * @param Board current game board.
 * @param Int id from client.
 * @return 0 if success, -1 if fail.
 */

fun performClientStep(board: Board, id: Int): Int {
    val n: Int = board.boardArray.size
    var point: Point = fromIdToPoint(id, n)
    // this cell is already occupied
    if (doStep(gameBoard, point, 1) == -1) {
        return -1
    }
    // check if the player won
    var res: Int? = isWin(gameBoard, point, 1)
    if (res != null) {
        gameBoard.winner = res
        return 0
    }
    // a tie
    if (gameBoard.possibleSteps.isEmpty()) {
        gameBoard.winner = 0
        return 0
    }
    // computer next step
    point = nextStep(gameBoard)
    doStep(gameBoard, point, -1)
    //check if computer wins
    res = isWin(gameBoard, point, -1)
    gameBoard.winner = res
    return 0
}