package com.wowbot.game.screen.raffle.render

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Align
import com.wowbot.assets.font.FontManager
import com.wowbot.assets.layout.GridLayout
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.game.robot.Robot


class RaffleInformationRender(private val context: EngineContext,
                              private val battles: List<Pair<Robot, Robot>>): GameObject {
    private val paddingTop = 4
    private val chunkSize = (battles.size / 2) + 1
    private val gridLayout = GridLayout((chunkSize * 3) + paddingTop, 100)
    private val chunks = battles.chunked(chunkSize)

    private val colors = arrayListOf<Color>(Color.GREEN, Color.CORAL,
            Color.FIREBRICK, Color.GOLD, Color.CYAN, Color.LIME)

    override fun render(context: EngineContext) {

        val fontManager = context.fontManager

        drawChunk(chunks[0],2, paddingTop)
        if (chunks.size == 2) {
            drawChunk(chunks[1],52, paddingTop)
        }

        drawTitle(fontManager, context)
    }

    private fun drawTitle(fontManager: FontManager, context: EngineContext) {
        val title = "Battles"
        val font = fontManager.font(fontManager.size(1), Color.BLUE)
        font.draw(context.batch, title, gridLayout.screenCenter.x.toFloat(),
                gridLayout.y(0, 20), 0f, Align.center, false)
    }

    private fun drawChunk(chunk: List<Pair<Robot, Robot>>, col: Int, paddingTop: Int) {
        var space = 0
        val defaultRowSize = 45
        val fontManager = context.fontManager

        chunk.forEachIndexed { index, robots ->

            val color = colors[index % colors.size]
            val scale = gridLayout.height / defaultRowSize
            val fontSize = (fontManager.size(1) * scale).toInt()
            val font = fontManager.font(fontSize, color)

            val rowA = paddingTop + (index * 2) - 1 + space
            val rowB = paddingTop + (index * 2) + space

            drawLine(col, rowA, rowB)

            drawNickname(font, robots.first, rowA, col)
            drawNickname(font, robots.second, rowB, col)
            space ++
        }
    }

    private fun drawLine(col: Int, rowA: Int, rowB: Int) {
        val shape = ShapeRenderer()
        shape.color = Color.BLUE
        val paddingLeft = 20f

        context.batch.end()
        shape.begin(ShapeRenderer.ShapeType.Filled)
        shape.rectLine(
                gridLayout.x(col) - paddingLeft,
                gridLayout.y(rowA),
                gridLayout.x(col) - paddingLeft,
                gridLayout.y(rowB + 1), 10f)
        shape.end()
        context.batch.begin()
    }

    private fun drawNickname(font: BitmapFont, robot: Robot, row: Int, col: Int) {
        val nickname = robot.nickname
        font.draw(context.batch,
                nickname,
                gridLayout.x(col),
                gridLayout.y(row),
                0f,
                Align.left,
                false)
    }

    override fun dispose() {}
}