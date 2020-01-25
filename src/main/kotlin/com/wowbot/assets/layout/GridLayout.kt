package com.wowbot.assets.layout

import com.wowbot.game.engine.EngineContext

class GridLayout(private val context: EngineContext, rows: Int, col: Int) {

    val rowSize = context.screenHeight / rows
    val colSize = context.screenWidth / col

    val centerX = context.screenWidth / 2
    val centerY = context.screenHeight / 2

    fun width(col: Int): Float {
        return context.screenWidth - col * colSize
    }

    fun height(row: Int): Float {
        return context.screenHeight - row * rowSize
    }
}