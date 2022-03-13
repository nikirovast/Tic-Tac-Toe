import models.Board
import models.Point
import kotlin.math.*

/**
 * Function to calculate the next good step for a computer.
 * @param Board the game board.
 * @return the point of the next step.
 */

fun nextStep(board: Board): Point {
    var bestScore: Int = Int.MIN_VALUE
    var alpha: Int = Int.MIN_VALUE
    val beta: Int = Int.MAX_VALUE
    var point: Point = board.possibleSteps.random()
    for (p in board.possibleSteps) {
        val copyBoard: Board = board.copyBoard()
        doStep(copyBoard, p, -1)
        // if found a winner step, do it
        if (isWin(copyBoard, p, -1) == -1) {
            return p
        }
        val score: Int = minimax(copyBoard, 0, false, alpha, beta)
        alpha = max(alpha, score)
        if (score >= beta) {
            continue
        }
        if (score > bestScore) {
            bestScore = score
            point = p
        }
    }
    return point
}

/**
 * Minimax recursive algorithm to find the next step.
 * @param Board the game board.
 * @param Int how deep to calculate.
 * @param Boolean minimaze or maximize.
 * @param Int alpha from alpha-beta proning algorithm.
 * @param Int beta from alpha-beta proning algorithm.
 * @return the score.
 */

fun minimax(board: Board, depth: Int, isMaximazing: Boolean, alpha: Int, beta: Int): Int {
    if (depth == 0) {
        if (isMaximazing)
            return analysePosition(board, -1)
        return analysePosition(board, 1)
    }
    var a: Int = alpha
    var b: Int = beta
    if (isMaximazing) {
        var bestScore: Int = Int.MIN_VALUE
        for (p in board.possibleSteps) {
            var copyBoard = board.copyBoard()
            doStep(copyBoard, p, 1)
            var score: Int = minimax(copyBoard, depth - 1, !isMaximazing, a, b)
            a = max(score, a)

            if (score >= b) {
                return score
            }
            bestScore = max(score, bestScore)
        }
        return bestScore
    }

    var bestScore: Int = Int.MAX_VALUE

    for (p in board.possibleSteps) {
        var copyBoard: Board = board.copyBoard()
        doStep(copyBoard, p, -1)
        var score: Int = minimax(copyBoard, depth - 1, !isMaximazing, a, b)
        b = min(score, b)

        if (score <= a) {
            return score
        }
        bestScore = min(score, bestScore)
    }

    return bestScore
}


