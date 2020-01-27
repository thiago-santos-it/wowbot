package com.wowbot.game.screen.winner.render

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Align
import com.wowbot.assets.layout.GridLayout
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.game.robot.Robot

class WinnerInformationRender(private val winner: Robot): GameObject {

    private val colors = arrayListOf<Color>(Color.GREEN, Color.CORAL,
            Color.FIREBRICK, Color.GOLD, Color.CYAN, Color.LIME, Color.BLUE)

    var elapsedTime = 0f

    override fun render(context: EngineContext) {
        val gridLayout = GridLayout(50, 100)

        val mainText = "Congrats ${winner.name} a.k.a. ${winner.nickname} you WIN!!"
        val mainFont = context.fontManager.font(context.fontManager.size(3), colors[(elapsedTime % colors.size).toInt()])
        mainFont.draw(context.batch, mainText, gridLayout.screenCenter.x.toFloat(),
                gridLayout.y(25), 0f, Align.center, false)
    }
}