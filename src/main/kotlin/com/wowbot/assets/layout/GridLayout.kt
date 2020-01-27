package com.wowbot.assets.layout

import com.badlogic.gdx.Gdx
import java.awt.Point

class GridLayout(rows: Int, col: Int) {

    private val screenWidth = Gdx.graphics.width.toFloat()
    private val screenHeight = Gdx.graphics.height.toFloat()

    val height = screenHeight / rows
    val width = screenWidth / col

    val screenCenter = Point((screenWidth / 2f).toInt(), (screenHeight / 2f).toInt())

    fun x(col: Int, padding: Int = 0): Float {
        return col * width + padding
    }

    fun y(row: Int, padding: Int = 0): Float {
        return screenHeight - (row * height + padding)
    }
}