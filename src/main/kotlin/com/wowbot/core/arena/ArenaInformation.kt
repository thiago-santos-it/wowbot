package com.wowbot.core.arena

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Align
import com.wowbot.assets.text.TextGridLayout
import com.wowbot.core.engine.EngineContext
import com.wowbot.core.engine.GameObject
import com.wowbot.core.robot.Robot

class ArenaInformation(val robot1: Robot, val robot2: Robot): GameObject {

    override fun render(context: EngineContext) {
        val layout = TextGridLayout(context, rows = 40, col = 100)
        val batch = context.batch

        val mainText = "VS."
        val mainFont = context.fontManager.font(layout.fontSize(1), Color.BLUE)
        mainFont.draw(batch, mainText, layout.centerX, layout.height(2), 0f, Align.center, false)

        val robotNicknameFont = context.fontManager.font(layout.fontSize(1), Color.GREEN)
        val robot1Nickname = robot1.nickname
        robotNicknameFont.draw(batch, robot1Nickname, layout.width(30), layout.height(2), 0f, Align.center, false)
        val robot2Nickname = robot2.nickname
        robotNicknameFont.draw(batch, robot2Nickname, layout.width(70), layout.height(2), 0f, Align.center, false)

        val robotOwnerFont = context.fontManager.font(layout.fontSize(3), Color.LIME)
        val robot1Owner = robot1.name
        robotOwnerFont.draw(batch, "($robot1Owner)", layout.width(30), layout.height(4), 0f, Align.center, false)
        val robot2Owner = robot2.name
        robotOwnerFont.draw(batch, "($robot2Owner)", layout.width(70), layout.height(4), 0f, Align.center, false)

        val lifeFont = context.fontManager.font(layout.fontSize(2), Color.GREEN)
        val life1Status = "${robot1.life}%"
        lifeFont.draw(batch, life1Status, layout.width(30), layout.height(38), 0f, Align.center, false)
        val life2Status = "${robot2.life}%"
        lifeFont.draw(batch, life2Status, layout.width(70), layout.height(38), 0f, Align.center, false)

    }
}