package components

import kotlinx.css.LinearDimension
import models.Point
import react.*
import styled.*
import kotlinx.css.*

external interface gameTableProps : Props {
    var boardArray: Array<Array<Int?>>
    var winner: Int?
    var winSquares: MutableSet<Point>
    var size: LinearDimension
    var cellClicked: (Int) -> Unit
}

val gameTable = fc<gameTableProps> { props ->
    styledTable {
        css {
            margin(0.px, LinearDimension.auto)
            width = props.size
            height = props.size
            borderSpacing = 0.px
            borderCollapse = BorderCollapse.collapse
        }
        val n: Int = props.boardArray.size
        val cellSize = props.size / 10
        for (i in 0 until n - 1) {
            styledTr {
                for (j in 0 until n - 1) {
                    styledTd {
                        val point = Point(i, j)
                        val item = props.boardArray[i][j]
                        child(cell) {
                            attrs {
                                id = point.getId(n)
                                value = item
                                isWinner = props.winSquares.contains(point)
                                onClickCell = { id -> props.cellClicked(id) }
                                size = cellSize
                                isClickable = props.winner == null && item == null
                            }
                        }
                    }
                }
            }
        }
    }
}