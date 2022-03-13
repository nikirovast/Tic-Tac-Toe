import components.gameTable
import components.button
import kotlinx.coroutines.*
import kotlinx.css.*
import models.BoardDTO
import react.*
import styled.*


private val scope = MainScope()

val styles = CssBuilder(allowClasses = false).apply {
    body {
        textAlign = TextAlign.center
        fontFamily = "sans-serif"
    }
}

object ComponentStyles : StyleSheet("ComponentStyles") {
    val pink_background by css {
        backgroundColor =  Color("#ff8282")
    }
}

val app = fc<Props> {
    var gameBoard by useState(BoardDTO())

    useEffectOnce {
        scope.launch {
            gameBoard = getBoard()
        }
    }

    injectGlobal(styles.toString())

    styledDiv {
        css {
            marginTop = 12.vmin
        }
        child(gameTable) {
            attrs {
                boardArray = gameBoard.boardArray
                winner = gameBoard.winner
                winSquares = gameBoard.winSquares
                size = 60.vmin
                cellClicked = { id ->
                    scope.launch {
                        updateSquare(id)
                        gameBoard = getBoard()
                    }
                }
            }
        }

        if (gameBoard.winner != null) {
            styledDiv {
                css {
                    marginTop = 2.em
                }
                child(button) {
                    attrs {
                        text = "new game"
                        onClickButton = {
                            scope.launch {
                                resetBoard()
                                gameBoard = getBoard()
                            }
                        }
                    }
                }
            }
        }
    }
}




