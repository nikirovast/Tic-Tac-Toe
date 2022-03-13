import models.Board
import kotlin.math.*

/**
 * Function to calculate overall score for a game.
 * @param Board the game board.
 * @param Int 1 if calculate for a player, -1 for a computer.
 * @param Int 1 if the next step is by a player, -1 by a computer.
 * @return the score.
 */

fun analysePosition(board: Board, currentTurn: Int): Int {
    val computerScore: Int = analyseSinglePlayer(board, -1, currentTurn)
    val playerScore: Int = analyseSinglePlayer(board, 1, currentTurn)
    val res: Int = computerScore - playerScore
    return res
}

/**
 * Function to calculate overall score for a player.
 * @param Board the game board.
 * @param Int 1 if calculate for a player, -1 for a computer.
 * @param Int 1 if the next step is by a player, -1 by a computer.
 * @return the score.
 */

fun analyseSinglePlayer(board: Board, player: Int, currentTurn: Int): Int {
    var res: Int = 0
    res += analyzeHorizontalSets(board, player, currentTurn)
    res += analyzeVerticalSets(board, player, currentTurn)
    res += analyzeDiagonalSets(board, player, currentTurn)
    res += analyzeAntiDiagonalSets(board, player, currentTurn)
    return res
}

/**
 * Function to calculate score for the horizontal blocks.
 * @param Board the game board.
 * @param Int 1 if calculate for a player, -1 for a computer.
 * @param Int 1 if the next step is by a player, -1 by a computer.
 * @return the score of horizontal blocks.
 */

fun analyzeHorizontalSets(board: Board, player: Int, currentTurn: Int): Int {
    var score: Int = 0
    var openEnds: Int = 0
    var count: Int = 0
    var n = board.boardArray.size
    for (i in 0 until n - 1) {
        for (j in 0 until n - 1) {
            if (board.boardArray[i][j] == player) {
                count++
            } else if (board.boardArray[i][j] == null && count > 0) {
                openEnds++
                score += getScore(count, openEnds, player == currentTurn)
                count = 0
                openEnds = 1
            } else if (board.boardArray[i][j] == null) {
                openEnds = 1
            } else if (count > 0) {
                score += getScore(count, openEnds, player == currentTurn)

                count = 0
                openEnds = 0
            } else {
                openEnds = 0
            }
        }
        if (count > 0) {
            score += getScore(count, openEnds, player == currentTurn)
            openEnds = 0
            count = 0
        }
    }
    return score
}

/**
 * Function to calculate score for the vertical blocks.
 * @param Board the game board.
 * @param Int 1 if calculate for a player, -1 for a computer.
 * @param Int 1 if the next step is by a player, -1 by a computer.
 * @return the score of vertical blocks.
 */

fun analyzeVerticalSets(board: Board, player: Int, currentTurn: Int): Int {
    var score: Int = 0
    var count: Int = 0
    var openEnds: Int = 0
    val n: Int = board.boardArray.size
    for (j in 0 until n - 1) {
        for (i in 0 until n - 1) {
            if (board.boardArray[i][j] == player) {
                count++
            } else if (board.boardArray[i][j] == null && count > 0) {
                openEnds++
                score += getScore(count, openEnds, player == currentTurn)
                count = 0
                openEnds = 1
            } else if (board.boardArray[i][j] == null) {
                openEnds = 1
            } else if (count > 0) {
                score += getScore(count, openEnds, player == currentTurn)
                count = 0
                openEnds = 0
            } else {
                openEnds = 0
            }
        }
        if (count > 0) {
            score += getScore(count, openEnds, player == currentTurn)
            openEnds = 0
            count = 0
        }
    }
    return score
}

/**
 * Function to calculate score for the diagonal blocks.
 * @param Board the game board.
 * @param Int 1 if calculate for a player, -1 for a computer.
 * @param Int 1 if the next step is by a player, -1 by a computer.
 * @return the score of diagonal blocks.
 */

fun analyzeDiagonalSets(board: Board, player: Int, currentTurn: Int): Int {
    var score: Int = 0
    var count: Int = 0
    var openEnds: Int = 0
    val n: Int = board.boardArray.size
    // lower part
    for (di in 0 until n - 5) {
        var j = 0
        for (i in di..n - 1) {
            if (board.boardArray[i][j] == player) {
                count++
            } else if (board.boardArray[i][j] == null && count > 0) {
                openEnds++
                score += getScore(count, openEnds, player == currentTurn)
                count = 0
                openEnds = 1
            } else if (board.boardArray[i][j] == null) {
                openEnds = 1
            } else if (count > 0) {
                score += getScore(count, openEnds, player == currentTurn)
                count = 0
                openEnds = 0
            } else {
                openEnds = 0
            }
            j++
        }
        if (count > 0) {
            score += getScore(count, openEnds, player == currentTurn)
            count = 0
            openEnds = 0
        }
    }
    // upper part
    for (dj in 1..n - 5) {
        var i = 0
        for (j in dj..n - 1) {
            if (board.boardArray[i][j] == player) {
                count++
            } else if (board.boardArray[i][j] == null && count > 0) {
                openEnds++
                score += getScore(count, openEnds, player == currentTurn)
                count = 0
                openEnds = 1
            } else if (board.boardArray[i][j] == null) {
                openEnds = 1
            } else if (count > 0) {
                score += getScore(count, openEnds, player == currentTurn)
                count = 0
                openEnds = 0
            } else {
                openEnds = 0
            }
            i++
        }
        if (count > 0) {
            score += getScore(count, openEnds, player == currentTurn)
            count = 0
            openEnds = 0
        }
    }
    return score
}

/**
 * Function to calculate score for the antidiagonal blocks.
 * @param Board the game board.
 * @param Int 1 if calculate for a player, -1 for a computer.
 * @param Int 1 if the next step is by a player, -1 by a computer.
 * @return the score of antidiagonal blocks.
 */

fun analyzeAntiDiagonalSets(board: Board, player: Int, currentTurn: Int): Int {
    var score: Int = 0
    var count: Int = 0
    var openEnds: Int = 0
    val n: Int = board.boardArray.size
    // upper part
    for (dj in 5..n - 2) {
        var i = 0
        var j = dj
        while (j >= 0) {
            if (board.boardArray[i][j] == player) {
                count++
            } else if (board.boardArray[i][j] == null && count > 0) {
                openEnds++
                score += getScore(count, openEnds, player == currentTurn)
                count = 0
                openEnds = 1
            } else if (board.boardArray[i][j] == null) {
                openEnds = 1
            } else if (count > 0) {
                score += getScore(count, openEnds, player == currentTurn)
                count = 0
                openEnds = 0
            } else {
                openEnds = 0
            }
            i++
            j--
        }
        if (count > 0) {
            score += getScore(count, openEnds, player == currentTurn)
            count = 0
            openEnds = 0
        }
    }
    // lower part
    for (di in 0 until n - 5) {
        var j = n - 1
        for (i in di until n - 1) {
            if (board.boardArray[i][j] == player) {
                count++
            } else if (board.boardArray[i][j] == null && count > 0) {
                openEnds++
                score += getScore(count, openEnds, player == currentTurn)
                count = 0
                openEnds = 1
            } else if (board.boardArray[i][j] == null) {
                openEnds = 1
            } else if (count > 0) {
                score += getScore(count, openEnds, player == currentTurn)
                count = 0
                openEnds = 0
            } else {
                openEnds = 0
            }
            j--
        }
        if (count > 0) {
            score += getScore(count, openEnds, player == currentTurn)
            count = 0
            openEnds = 0
        }
    }
    return score
}

/**
 * Function to get the score for current block on the board.
 *
 * @param Int number of blocks in a row.
 * @param Int number of open ends for these blocks.
 * @param Boolean current turn or not.
 * @return the score for this position.
 */

fun getScore(count: Int, openEnds: Int, currentTurn: Boolean): Int {
    /* numbers are taken from here
       https://blog.theofekfoundation.org/artificial-intelligence/2015/12/11/minimax-for-gomoku-connect-five/
    */
    if (openEnds == 0 && count < 5) {
        return 0
    }
    when (count) {
        5 -> {
            return 1000000000
        }
        4 -> {
            when (openEnds) {
                1 -> {
                    if (currentTurn) {
                        return 1000000000
                    }
                    return 500
                }
                2 -> {
                    if (currentTurn) {
                        return 1000000000
                    }
                    return 5000000
                }
            }
        }
        3 -> {
            when (openEnds) {
                1 -> {
                    if (currentTurn) {
                        return 70
                    }
                    return 50
                }
                2 -> {
                    if (currentTurn) {
                        return 100000
                    }
                    return 500
                }
            }
        }
        2 -> {
            when (openEnds) {
                1 -> {
                    return 20
                }
                2 -> {
                    return 50
                }
            }
        }
        1 -> {
            when (openEnds) {
                1 -> {
                    return 5
                }
                2 -> {
                    return 10
                }
            }
        }
    }
    return 2000000000
}

