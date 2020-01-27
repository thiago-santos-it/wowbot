package com.wowbot.game.robot

import com.badlogic.gdx.Gdx
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.extensions.toRadians
import com.wowbot.game.robot.render.RobotRender
import com.wowbot.game.script.Script
import org.lwjgl.util.Point
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class Robot(private val script: Script): GameObject {

    enum class Action {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT,
        CANNON_LEFT,
        NOTHING,
        CANNON_RIGHT,
        FIRE,
        FIRE_HARD
    }

    private var cannon: Cannon? = null
    private var robotRender: RobotRender? = null
    private var currentAction: String? = null

    private var point: Point? = null
    private var elapsedSteps: Float = 0f
    private var typeA: Boolean = false

    private val stepSize = 10
    private val actionStepsDuration = 10f

    var life = 100f
    val name: String = script.inspect("name") ?: "No name"
    val nickname: String = script.inspect("nickname") ?: "No nickname"

    private val battleContext = BattleContext(false)

    fun load(typeA: Boolean) {

        this.typeA = typeA

        robotRender = RobotRender(nickname, typeA)
        cannon = Cannon(typeA)
        cannon?.load()
        robotRender?.load()
    }

    override fun render(context: EngineContext) {
        randomPositionIfNeeded(context)

        robotRender?.point = this.point
        robotRender?.render(context)

        cannon?.point = this.point
        cannon?.render(context)

        if (elapsedSteps > actionStepsDuration) {
            elapsedSteps = 0f
            currentAction = script.run(battleContext.toMap()) ?: "${Action.NOTHING}"
        }

        elapsedSteps++
        performAction(currentAction)
    }

    private fun randomPositionIfNeeded(context: EngineContext) {
        if (this.point == null && this.robotRender != null) {
            val x = (context.screenWidth - 10 - (robotRender?.width() ?: 0)).toInt()
            val y = Random.nextInt(robotRender?.height() ?: 0, (context.screenHeight - (robotRender?.height() ?: 0)).toInt())
            this.point = Point(if (typeA) { 10 } else { x }, y)
        }
    }

    private fun performAction(action: String?) {
        if (action == null) { return }
        try {
            when (Action.valueOf(action)) {
                Action.FORWARD -> move(1)
                Action.BACKWARD -> move(-1)
                Action.LEFT -> robotRender?.rotateLeft()
                Action.RIGHT -> robotRender?.rotateRight()
                Action.CANNON_LEFT -> cannon?.rotateLeft()
                Action.CANNON_RIGHT -> cannon?.rotateRight()
                Action.FIRE -> cannon?.fire()
                Action.FIRE_HARD -> cannon?.fireWithForce()
                Action.NOTHING -> {
                }
            }
        } catch (e: Exception) {
            print("Invalid action")
        }
    }

    private fun move(direction: Int) {
        val localPoint = point ?: return
        val angle = robotRender?.currentAngle() ?: 0f
        if (robotRender != null) {

            val x = stepSize * cos(angle.toRadians())
            val y = stepSize * sin(angle.toRadians())

            val futurePoint = Point(localPoint.x + direction * x.toInt(), localPoint.y + direction * y.toInt())

            val width = Gdx.graphics.width - (robotRender?.width() ?: 0)
            val height = Gdx.graphics.height - (robotRender?.height() ?: 0)

            val hitTheWall = futurePoint.x > width || futurePoint.x < 0 || futurePoint.y < 0 || futurePoint.y > height
            if (!hitTheWall) {
                localPoint.x = futurePoint.x
                localPoint.y = futurePoint.y
            }
            battleContext.hitTheWall = !hitTheWall
        }
    }
}