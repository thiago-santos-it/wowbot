package com.wowbot.game.screen.battle

import com.wowbot.game.championship.ChampionshipManager
import com.wowbot.game.collision.CollisionManager
import com.wowbot.game.screen.battle.render.BattleBackgroundRender
import com.wowbot.game.screen.battle.render.BattleInformationRender
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.ui.RenderScreen
import com.wowbot.game.robot.Robot
import com.wowbot.game.robot.context.BattleContext
import com.wowbot.game.robot.context.RobotContext
import kotlin.math.hypot


class BattleScreen(
        private val context: EngineContext,
        private val robots: Pair<Robot, Robot>,
        private val championshipManager: ChampionshipManager,
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

        CollisionManager.detect()

        nextIfNeeded(delta)

        val robotAContext = robots.first.context()
        val robotBContext = robots.second.context()

        robots.first.scriptParameters = robotContextPatameters(robotAContext, robotBContext)
        robots.first.render(context)

        robots.second.scriptParameters = robotContextPatameters(robotBContext, robotAContext)
        robots.second.render(context)
    }

    fun robotContextPatameters(robot: RobotContext, opponent: RobotContext): Map<String, Any> {

        val opponentUp = robot.y < opponent.y
        val opponentDown = !opponentUp
        val opponentLeft = robot.x > opponent.x
        val opponentRight = !opponentLeft
        val opponentDistance = hypot((robot.x - opponent.x).toFloat(), (robot.y - opponent.y).toFloat())

        return BattleContext(
                robotContext = robot,
                fieldWidth = context.screenWidth,
                fieldHeight = context.screenHeight,
                opponentLife = opponent.life,
                opponentUp = opponentUp,
                opponentDown = opponentDown,
                opponentLeft = opponentLeft,
                opponentRight = opponentRight,
                opponentDistance = opponentDistance
                ).toMap()
    }

    private fun nextIfNeeded(delta: Float) {
        if (robots.first.life <= 0 || robots.second.life <= 0) {

            robots.first.stop = true
            robots.second.stop = true

            endGameElapsedTime += delta

            if (endGameElapsedTime > engGameWaitInSeconds) {

                if (robots.first.life <= 0) {
                    championshipManager.loser(robots.first)
                    championshipManager.winner(robots.second)
                } else if (robots.second.life <= 0) {
                    championshipManager.winner(robots.first)
                    championshipManager.loser(robots.second)
                }
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