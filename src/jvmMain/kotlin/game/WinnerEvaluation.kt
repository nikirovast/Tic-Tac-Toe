import models.Board
import models.Point

/**
 * Function to check if someone won the game with the last step.
 * @param Board the game board.
 * @param Point the last step.
 * @param Int 1 if the last step was by player, -1 by computer.
 * @return the winner or null if no one won so far
 */

fun isWin(board: Board, point: Point, player: Int): Int? {
    //a tie
    if (board.possibleSteps.isEmpty()) {
        return 0
    }
    // check horizontal
    var res: Int? = checkHorizontal(board, point, player)
    if (res != null) {
        return player
    }
    // check vertical
    res = checkVertical(board, point, player)
    if (res != null) {
        return player
    }
    // check diagonal
    res = checkDiagonal(board, point, player)
    if (res != null) {
        return player
    }
    // check antidiagonal
    res = checkAntiDiagonal(board, point, player)
    if (res != null) {
        return player
    }
    return null
}

/**
 * Function to check if someone won the game with the last step in horizontal direction.
 * @param Board the game board.
 * @param Point the last step.
 * @param Int 1 if the last step was by player, -1 by computer.
 * @return the winner or null if no one won so far
 */

fun checkHorizontal(board: Board, point: Point, player: Int): Int? {
    var count: Int = 1
    val row: Int = point.x
    val column: Int = point.y
    var toWin: Int = board.numToWin
    var p: Int = column - 1
    val n: Int = board.boardArray.size

    board.winSquares.add(point)
    // going left
    while (p > -1) {
        if (board.boardArray[row][p] != player) {
            break
        }
        board.winSquares.add(Point(row, p))
        count++
        p--
        if (count == toWin) {
            return player
        }
    }
    // going right
    p = column + 1
    while (p < n) {
        if (board.boardArray[row][p] != player) {
            break
        }
        board.winSquares.add(Point(row, p))
        count++
        p++
        if (count == toWin) {
            return player
        }
    }
    if (count == toWin) {
        return player
    }
    board.winSquares.clear()
    return null
}

/**
 * Function to check if someone won the game with the last step in vertical direction.
 * @param Board the game board.
 * @param Point the last step.
 * @param Int 1 if the last step was by player, -1 by computer.
 * @return the winner or null if no one won so far
 */

fun checkVertical(board: Board, point: Point, player: Int): Int? {
    var count: Int = 1
    var row: Int = point.x
    var column: Int = point.y
    var toWin: Int = board.numToWin
    var p: Int = row - 1
    val n: Int = board.boardArray.size

    board.winSquares.add(point)
    // going left
    while (p > -1) {
        if (board.boardArray[p][column] != player) {
            break
        }
        board.winSquares.add(Point(p, column))
        count++
        p--
        if (count == toWin) {
            return player
        }
    }
    // going right
    p = row + 1
    while (p < n) {
        if (board.boardArray[p][column] != player) {
            break
        }
        board.winSquares.add(Point(p, column))
        count++
        p++
        if (count == toWin) {
            return player
        }
    }
    if (count == toWin) {
        return player
    }
    board.winSquares.clear()
    return null
}

/**
 * Function to check if someone won the game with the last step in diagonal direction.
 * @param Board the game board.
 * @param Point the last step.
 * @param Int 1 if the last step was by player, -1 by computer.
 * @return the winner or null if no one won so far
 */

fun checkDiagonal(board: Board, point: Point, player: Int): Int? {
    var count: Int = 1
    var row: Int = point.x
    var column: Int = point.y
    var toWin: Int = board.numToWin
    var pointRow: Int = row - 1
    var pointColumn: Int = column + 1
    var n: Int = board.boardArray.size

    board.winSquares.add(point)
    // going left
    while (pointRow > -1 && pointColumn < n) {
        if (board.boardArray[pointRow][pointColumn] != player) {
            break
        }
        board.winSquares.add(Point(pointRow, pointColumn))
        count++
        pointRow--
        pointColumn++
        if (count == toWin) {
            return player
        }
    }
    // going right
    pointRow = row + 1
    pointColumn = column - 1

    while (pointColumn > -1 && pointRow < n) {
        if (board.boardArray[pointRow][pointColumn] != player) {
            break
        }
        board.winSquares.add(Point(pointRow, pointColumn))
        count++
        pointColumn--
        pointRow++
        if (count == toWin) {
            return player
        }
    }
    if (count == toWin) {
        return player
    }
    board.winSquares.clear()
    return null
}

/**
 * Function to check if someone won the game with the last step in antidiagonal direction.
 * @param Board the game board.
 * @param Point the last step.
 * @param Int 1 if the last step was by player, -1 by computer.
 * @return the winner or null if no one won so far
 */

fun checkAntiDiagonal(board: Board, point: Point, player: Int): Int? {
    var count: Int = 1
    var row: Int = point.x
    var column: Int = point.y
    var toWin: Int = board.numToWin
    var pointRow: Int = row - 1
    var pointColumn: Int = column - 1
    var n: Int = board.boardArray.size

    board.winSquares.add(point)
    // going left
    while (pointRow > -1 && pointColumn > -1) {
        if (board.boardArray[pointRow][pointColumn] != player) {
            break
        }
        board.winSquares.add(Point(pointRow, pointColumn))
        count++
        pointRow--
        pointColumn--
        if (count == toWin) {
            return player
        }
    }
    // going right
    pointRow = row + 1
    pointColumn = column + 1

    while (pointColumn < n && pointRow < n) {
        if (board.boardArray[pointRow][pointColumn] != player) {
            break
        }
        board.winSquares.add(Point(pointRow, pointColumn))
        count++
        pointColumn++
        pointRow++
        if (count == toWin) {
            return player
        }
    }
    if (count == toWin) {
        return player
    }
    board.winSquares.clear()
    return null
}
