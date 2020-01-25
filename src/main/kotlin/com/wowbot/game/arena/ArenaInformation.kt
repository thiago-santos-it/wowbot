package com.wowbot.game.arena

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Align
import com.wowbot.assets.layout.GridLayout
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.game.robot.Robot

class ArenaInformation: GameObject {

    var currentRobots: Pair<Robot, Robot>? = null

    override fun render(context: EngineContext) {
        val robots = currentRobots ?: return

        val layout = GridLayout(context, rows = 40, col = 100)
        val batch = context.batch

        val mainText = "VS."
        val mainFont = context.fontManager.font(layout.fontSize(1), Color.BLUE)
        mainFont.draw(batch, mainText, layout.centerX, layout.height(2), 0f, Align.center, false)

        val robotNicknameFont = context.fontManager.font(layout.fontSize(1), Color.GREEN)
        val robot1Nickname = robots.first.nickname
        robotNicknameFont.draw(batch, robot1Nickname, layout.width(30), layout.height(2), 0f, Align.center, false)
        val robot2Nickname = robots.second.nickname
        robotNicknameFont.draw(batch, robot2Nickname, layout.width(70), layout.height(2), 0f, Align.center, false)

        val robotOwnerFont = context.fontManager.font(layout.fontSize(3), Color.LIME)
        val robot1Owner = robots.first.name
        robotOwnerFont.draw(batch, "($robot1Owner)", layout.width(30), layout.height(4), 0f, Align.center, false)
        val robot2Owner = robots.second.name
        robotOwnerFont.draw(batch, "($robot2Owner)", layout.width(70), layout.height(4), 0f, Align.center, false)

        val lifeFont = context.fontManager.font(layout.fontSize(2), Color.GREEN)
        val life1Status = "${robots.first.life}%"
        lifeFont.draw(batch, life1Status, layout.width(30), layout.height(38), 0f, Align.center, false)
        val life2Status = "${robots.second.life}%"
        lifeFont.draw(batch, life2Status, layout.width(70), layout.height(38), 0f, Align.center, false)

    }
}