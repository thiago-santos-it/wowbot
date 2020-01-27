package com.wowbot.game.screen.battle.render

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Align
import com.wowbot.assets.layout.GridLayout
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.game.engine.ui.ProgressBar
import com.wowbot.game.robot.Robot

class BattleInformationRender: GameObject {

    private val rows = 40
    private val columns = 100
    private val gridLayout = GridLayout(rows = 40, col = 100)

    private val progressBarA = ProgressBar((gridLayout.x(columns/2) - gridLayout.x(5)).toInt(), 30)
    private val progressBarB = ProgressBar((gridLayout.x(columns/2) - gridLayout.x(5)).toInt(), 30)

    var robots: Pair<Robot, Robot>? = null

    override fun render(context: EngineContext) {

        val robots = robots ?: return
        val fontManager = context.fontManager

        val batch = context.batch

        val mainText = "VS"
        val mainFont = fontManager.font(fontManager.size(1), Color.BLUE)
        mainFont.draw(batch, mainText, gridLayout.screenCenter.x.toFloat(), gridLayout.y(1), 0f, Align.center, false)

        progressBarA.setPosition(gridLayout.x(2), gridLayout.y(2))
        progressBarB.setPosition(gridLayout.x(columns / 2 + 4), gridLayout.y(2))

        progressBarA.value = robots.first.life
        progressBarB.value = robots.second.life

        progressBarA.draw(context.batch, 1f)
        progressBarB.draw(context.batch, 1f)

        val robotNicknameFont = fontManager.font(fontManager.size(3), Color.GREEN)
        val robot1Nickname = robots.first.nickname
        robotNicknameFont.draw(batch, robot1Nickname, gridLayout.x(25), gridLayout.y(2, 10), 0f, Align.center, false)
        val robot2Nickname = robots.second.nickname
        robotNicknameFont.draw(batch, robot2Nickname, gridLayout.x(75), gridLayout.y(2, 10), 0f, Align.center, false)

        val robotOwnerFont = fontManager.font(fontManager.size(4), Color.LIME)
        val robot1Owner = robots.first.name
        robotOwnerFont.draw(batch, "($robot1Owner)", gridLayout.x(25), gridLayout.y(4), 0f, Align.center, false)
        val robot2Owner = robots.second.name
        robotOwnerFont.draw(batch, "($robot2Owner)", gridLayout.x(75), gridLayout.y(4), 0f, Align.center, false)

    }
}