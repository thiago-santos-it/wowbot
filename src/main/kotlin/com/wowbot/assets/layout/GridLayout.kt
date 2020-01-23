package com.wowbot.assets.layout

import com.wowbot.core.engine.EngineContext

class GridLayout(private val context: EngineContext, rows: Int, col: Int) {

    private val rowSize = context.screenHeight / rows
    private val colSize = context.screenWidth / col
    private val headers = arrayListOf(60, 50, 40, 30, 20, 10, 5)

    val centerX = context.screenWidth / 2
    val centerY = context.screenHeight / 2

    fun width(col: Int): Float {
        return context.screenWidth - col * colSize
    }

    fun height(row: Int): Float {
        return context.screenHeight - row * rowSize
    }

    fun fontSize(h: Int): Int {
        return headers[h - 1]
    }
}