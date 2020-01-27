package com.wowbot.game.screen.battle

import com.wowbot.game.screen.battle.render.BattleBackgroundRender
import com.wowbot.game.screen.battle.render.BattleInformationRender
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.ui.RenderScreen
import com.wowbot.game.robot.Robot


class BattleScreen(
        private val context: EngineContext,
        private val robots: Pair<Robot, Robot>,
        private val next: () -> Unit): RenderScreen() {

    private val arenaBackground = BattleBackgroundRender()

    private val arenaInformation = BattleInformationRender()

    private val engGameWaitInSeconds = 3
    private var endGameElapsedTime = 0f

    init {
        arenaBackground.load()
        arenaInformation.load()

        robots.first.load(true)
        robots.second.load(false)

        arenaInformation.robots = robots
    }

    override fun render(delta: Float) {
        arenaBackground.render(context)
        arenaInformation.render(context)

        nextIfNeeded(delta)

        robots.first.render(context)
        robots.second.render(context)
    }

    private fun nextIfNeeded(delta: Float) {
        if (robots.first.life <= 0 || robots.second.life <= 0) {
            endGameElapsedTime += delta
            if (endGameElapsedTime > engGameWaitInSeconds) {
                next()
            }
        }
    }

    override fun dispose() {
        arenaBackground.dispose()
        arenaInformation.dispose()
        robots.first.dispose()
        robots.second.dispose()
    }
}