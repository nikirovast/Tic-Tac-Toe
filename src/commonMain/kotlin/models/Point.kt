package models

import kotlinx.serialization.Serializable

/**
 * This class is used to create a board Point object.
 * @param Int horizontal coordinate.
 * @param Int vertical coordinate.
 * @constructor Creates a Point with this coordinates.
 */

@Serializable
data class Point(var x: Int, var y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Point

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    /**
     * This class calculates 1D point id.
     * @param Int size of the board.
     * @return 1D point id.
     */

    fun getId(n: Int): Int {
        val res: Int = x * n + y
        return res
    }
}