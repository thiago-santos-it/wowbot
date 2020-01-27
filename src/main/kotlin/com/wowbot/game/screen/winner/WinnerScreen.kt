package com.wowbot.game.screen.winner

import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.ui.RenderScreen
import com.wowbot.game.robot.Robot
import com.wowbot.game.screen.winner.render.WinnerInformationRender

class WinnerScreen(
        private val context: EngineContext,
        winner: Robot): RenderScreen() {

    private  val winnerInformation = WinnerInformationRender(winner)

    override fun render(delta: Float) {
        winnerInformation.elapsedTime += delta
        winnerInformation.render(context)
    }

    override fun dispose() {
    }
}