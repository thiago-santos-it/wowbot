package com.wowbot.game.arena

import com.wowbot.game.engine.AssetController
import com.wowbot.game.robot.Robot

class ChampionshipManager(private val robots: List<Robot>): AssetController {

    private val winners = mutableListOf<Robot>()
    private val losers = mutableListOf<Robot>()
    private val battles = mutableListOf<Pair<Robot, Robot>>()

    override fun load() {
        raffle(robots)
    }

    fun next(): Pair<Robot, Robot>? {
        return if (battles.isNotEmpty()) {
            battles.removeAt(0)
        } else {
            null
        }
    }

    fun nextLevel() {
        battles.clear()
        raffle(winners)
        winners.clear()
    }

    fun winner(robot: Robot) {
        winners.add(robot)
    }

    fun loser(robot: Robot) {
        losers.add(robot)
    }

    private fun raffle(competitors: List<Robot>): List<Pair<Robot, Robot>> {
        val mutableCompetitors = mutableListOf<Robot>()
        mutableCompetitors.addAll(competitors)

        var pair: Pair<Robot, Robot>? = this.rafflePair(mutableCompetitors)

        while (pair != null) {
            battles.add(pair)
            mutableCompetitors.remove(pair.first)
            mutableCompetitors.remove(pair.second)
            pair = this.rafflePair(mutableCompetitors)
        }
        return battles
    }

    private fun rafflePair(competitors: List<Robot>): Pair<Robot, Robot>? {
        return if (competitors.size < 2) {
            winners.addAll(competitors)
            null
        } else {
            val robotA = competitors.first()
            val robotB = competitors.last()
            Pair(robotA, robotB)
        }
    }

    override fun dispose() {
        winners.clear()
        losers.clear()
        battles.clear()
    }

}