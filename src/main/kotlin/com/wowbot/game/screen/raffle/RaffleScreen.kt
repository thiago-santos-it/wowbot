package com.wowbot.game.screen.raffle

import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.ui.RenderScreen
import com.wowbot.game.robot.Robot
import com.wowbot.game.screen.raffle.render.RaffleInformationRender

class RaffleScreen(
        private val context: EngineContext,
        battles: List<Pair<Robot, Robot>>,
        private val next: () -> Unit): RenderScreen() {

    private val raffleInformationRender = RaffleInformationRender(context, battles)

    private var elapsedTime = 0f

    override fun show() {
        super.show()
        raffleInformationRender.load()
    }

    override fun render(delta: Float) {
        elapsedTime += delta
        raffleInformationRender.render(context)
        if (elapsedTime > displayTime) {
            elapsedTime = 0f
            next()
        }
    }

    override fun dispose() {
        raffleInformationRender.dispose()
    }

    companion object {
        private const val displayTime = 0.1
    }
}