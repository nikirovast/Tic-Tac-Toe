package components

import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import models.Point
import react.Props
import react.fc
import styled.css
import styled.styledButton

external interface buttonProps : Props {
    var text: String
    var onClickButton: () -> Unit
}

val button = fc<buttonProps> { props ->
    styledButton {
        css {
            height = 6.vmin
            width = 22.vmin
            border = "none"
            borderRadius = 1.vmin
            color = Color.white
            fontSize = 3.vmin
            +ComponentStyles.pink_background

            hover {
                backgroundColor = Color("#ffa7a7")
                cursor = Cursor.pointer
            }
        }

        +props.text

        attrs.onClickFunction = {
            props.onClickButton()
        }
    }
}
