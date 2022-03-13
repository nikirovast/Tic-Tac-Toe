package components

import kotlinx.css.LinearDimension
import kotlinx.html.js.onClickFunction
import models.Point
import react.*
import styled.*
import kotlinx.css.*

external interface cellProps : Props {
    var id: Int
    var value: Int?
    var isWinner: Boolean
    var onClickCell: (Int) -> Unit
    var size: LinearDimension
    var isClickable: Boolean
}

val cell = fc<cellProps> { props ->
    styledDiv {
        css {
            height = props.size
            width = props.size
            alignItems = Align.center
            fontSize = props.size * 0.8
            fontWeight = FontWeight.bolder

            if (props.value == 1) {
                color = Color("#595758")
            } else {
                color = Color("#595758")
            }
            if (props.isWinner) {
                +ComponentStyles.pink_background
            } else {
                backgroundColor = Color("#e0dbdc")
            }
            if (props.isClickable) {
                cursor = Cursor.pointer
            } else {
                cursor = Cursor.notAllowed
            }
        }
        if (props.value == 1) {
            +"x"
        } else if (props.value == -1) {
            +"o"
        }
        if (props.isClickable) {
            attrs.onClickFunction = {
                props.onClickCell(props.id)
            }
        }
    }
}