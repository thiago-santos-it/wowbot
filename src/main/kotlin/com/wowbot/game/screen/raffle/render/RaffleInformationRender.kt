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

    override fun render(context: EngineContext) {
        //TODO avoid text sobreposition with huge number of robots. (1) create more columns and adapt font size

        val paddingTop = 4
        val chunkSize = (battles.size / 2) + 1
        val gridLayout = GridLayout(context, (chunkSize * 3) + paddingTop, 100)
        val fontLayout = FontLayout()

        val fontSize = fontLayout.size(4)
        val colors = arrayListOf<Color>(Color.GREEN, Color.CORAL,
                Color.FIREBRICK, Color.GOLD, Color.CYAN, Color.LIME, Color.BLUE)
        val chunks = battles.chunked(chunkSize)

        var space = 0
        chunks[0].forEachIndexed { index, robots ->
            val color = colors[index % colors.size]
            val font = context.fontManager.font(fontSize, color)
            drawNickname(gridLayout, font, robots.first, paddingTop + (index * 2) - 1 + space, 98)
            drawNickname(gridLayout, font, robots.second, paddingTop + (index * 2) + space, 98)
            space ++
        }

        space = 0
        chunks[1].forEachIndexed { index, robots ->
            val color = colors[index % colors.size]
            val font = context.fontManager.font(fontSize, color)
            drawNickname(gridLayout, font, robots.first, paddingTop + (index * 2) - 1 + space, 48)
            drawNickname(gridLayout, font, robots.second, paddingTop + (index * 2) + space, 48)
            space ++
        }

        val mainText = "Battles"
        val mainTextPaddingTop = 20
        val mainFont = context.fontManager.font(fontLayout.size(1), Color.BLUE)
        mainFont.draw(context.batch, mainText, gridLayout.centerX,
                gridLayout.height(0) - mainTextPaddingTop, 0f, Align.center, false)

    }

    fun drawNickname(gridLayout: GridLayout, font: BitmapFont, robot: Robot, row: Int, col: Int) {
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