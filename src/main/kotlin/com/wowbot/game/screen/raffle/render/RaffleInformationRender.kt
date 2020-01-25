package com.wowbot.game.screen.raffle.render

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.Align
import com.wowbot.assets.layout.FontLayout
import com.wowbot.assets.layout.GridLayout
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.game.robot.Robot

class RaffleInformationRender(private val context: EngineContext,
                              private val battles: List<Pair<Robot, Robot>>): GameObject {

    private val colors = arrayListOf<Color>(Color.GREEN, Color.CORAL,
            Color.FIREBRICK, Color.GOLD, Color.CYAN, Color.LIME, Color.BLUE)

    override fun render(context: EngineContext) {
        //TODO avoid text sobreposition with huge number of robots. (1) create more columns and adapt font size
        val paddingTop = 4
        val chunkSize = (battles.size / 2) + 1
        val gridLayout = GridLayout(context, (chunkSize * 3) + paddingTop, 100)
        val chunks = battles.chunked(chunkSize)

        drawChunk(chunks[0], gridLayout, 98, paddingTop)
        if (chunks.size == 2) {
            drawChunk(chunks[1], gridLayout, 48, paddingTop)
        }

        val mainText = "Battles"
        val mainTextPaddingTop = 20
        val mainFont = context.fontManager.font(FontLayout().size(1), Color.BLUE)
        mainFont.draw(context.batch, mainText, gridLayout.centerX,
                gridLayout.height(0) - mainTextPaddingTop, 0f, Align.center, false)

    }

    private fun drawChunk(chunk: List<Pair<Robot, Robot>>, gridLayout: GridLayout, col: Int, paddingTop: Int) {
        var space = 0
        val fontSize = FontLayout().size(4)

        chunk.forEachIndexed { index, robots ->
            val color = colors[index % colors.size]
            val font = context.fontManager.font(fontSize, color)
            drawNickname(gridLayout, font, robots.first, paddingTop + (index * 2) - 1 + space, col)
            drawNickname(gridLayout, font, robots.second, paddingTop + (index * 2) + space, col)
            space ++
        }
    }

    private fun drawNickname(gridLayout: GridLayout, font: BitmapFont, robot: Robot, row: Int, col: Int) {
        val nickname = robot.nickname
        font.draw(context.batch,
                nickname,
                gridLayout.width(col),
                gridLayout.height(row),
                0f,
                Align.left,
                false)
    }
}