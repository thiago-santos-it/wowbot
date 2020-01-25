package com.wowbot.game.screen.battle.render

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Align
import com.wowbot.assets.layout.FontLayout
import com.wowbot.assets.layout.GridLayout
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.game.robot.Robot

class BattleInformationRender: GameObject {

    var robots: Pair<Robot, Robot>? = null

    override fun render(context: EngineContext) {
        val robots = robots ?: return

        val gridLayout = GridLayout(context, rows = 40, col = 100)
        val fontLayout = FontLayout()
        val batch = context.batch

        val mainText = "VS."
        val mainFont = context.fontManager.font(fontLayout.size(1), Color.BLUE)
        mainFont.draw(batch, mainText, gridLayout.centerX, gridLayout.height(2), 0f, Align.center, false)

        val robotNicknameFont = context.fontManager.font(fontLayout.size(1), Color.GREEN)
        val robot1Nickname = robots.first.nickname
        robotNicknameFont.draw(batch, robot1Nickname, gridLayout.width(30), gridLayout.height(2), 0f, Align.center, false)
        val robot2Nickname = robots.second.nickname
        robotNicknameFont.draw(batch, robot2Nickname, gridLayout.width(70), gridLayout.height(2), 0f, Align.center, false)

        val robotOwnerFont = context.fontManager.font(fontLayout.size(3), Color.LIME)
        val robot1Owner = robots.first.name
        robotOwnerFont.draw(batch, "($robot1Owner)", gridLayout.width(30), gridLayout.height(4), 0f, Align.center, false)
        val robot2Owner = robots.second.name
        robotOwnerFont.draw(batch, "($robot2Owner)", gridLayout.width(70), gridLayout.height(4), 0f, Align.center, false)

        val lifeFont = context.fontManager.font(fontLayout.size(2), Color.GREEN)
        val life1Status = "${robots.first.life}%"
        lifeFont.draw(batch, life1Status, gridLayout.width(30), gridLayout.height(38), 0f, Align.center, false)
        val life2Status = "${robots.second.life}%"
        lifeFont.draw(batch, life2Status, gridLayout.width(70), gridLayout.height(38), 0f, Align.center, false)
    }
}