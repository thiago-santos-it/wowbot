package com.wowbot.game.screen

import com.badlogic.gdx.Game
import com.wowbot.game.championship.ChampionshipManager
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.screen.battle.BattleScreen
import com.wowbot.game.screen.raffle.RaffleScreen
import com.wowbot.game.screen.winner.WinnerScreen

class SceneManager(
        private val game: Game,
        private val context: EngineContext,
        private val championshipManager: ChampionshipManager){

    fun start() {
        game.screen = RaffleScreen(context, championshipManager.battles) { nextScreen() }
    }

    private fun nextScreen() {
        val robots = championshipManager.next()

        if (robots == null) {

            val winners = championshipManager.winners
            championshipManager.nextLevel()

            if (winners.size == 1) {
                game.screen = WinnerScreen(context, winners.first())
            } else {
                game.screen = RaffleScreen(context, championshipManager.battles) { nextScreen() }
            }
        } else {
            game.screen = BattleScreen(context, robots) { nextScreen() }
        }
    }
}