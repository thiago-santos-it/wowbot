package com.wowbot.game.screen.winner

import com.wowbot.game.core.engine.EngineContext
import com.wowbot.game.core.engine.RenderScreen
import com.wowbot.game.robot.Robot

class WinnerScreen(
        private val context: EngineContext,
        private val winner: Robot): RenderScreen() {

    override fun render(delta: Float) {
    }

    override fun dispose() {
    }
}