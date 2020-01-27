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

    private var point: Point = Point(400, 400)
    private var elapsedSteps: Float = 0f

    private val stepSize = 10
    private val actionStepsDuration = 10f

    var life = 100f
    val name: String = script.inspect("name") ?: "No name"
    val nickname: String = script.inspect("nickname") ?: "No nickname"

    private val battleContext = BattleContext(false)

    fun load(typeA: Boolean) {
        robotRender = RobotRender(typeA)
        cannon = Cannon(typeA)
        cannon?.load()
        robotRender?.load()
    }

    override fun render(context: EngineContext) {

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

    private fun performAction(action: String?) {
        if (action == null) { return }
        try {
            when (Action.valueOf(action)) {
                Action.FORWARD -> forward()
                Action.BACKWARD -> backward()
                Action.LEFT -> rotateLeft()
                Action.RIGHT -> rotateRight()
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

    private fun forward() {
        move(1)
    }

    private fun backward() {
        move(-1)
    }

    private fun rotateLeft() {
        robotRender?.rotateLeft()

    }

    private fun rotateRight() {
        robotRender?.rotateRight()
    }

    private fun move(direction: Int) {
        val angle = robotRender?.currentAngle() ?: 0f
        if (robotRender != null) {

            val x = stepSize * cos(angle.toRadians())
            val y = stepSize * sin(angle.toRadians())

            val futurePoint = Point(this.point.x + direction * x.toInt(), this.point.y + direction * y.toInt())

            val width = Gdx.graphics.width - (robotRender?.width() ?: 0)
            val height = Gdx.graphics.height - (robotRender?.height() ?: 0)

            val hitTheWall = futurePoint.x > width || futurePoint.x < 0 || futurePoint.y < 0 || futurePoint.y > height
            if (!hitTheWall) {
                this.point.x = futurePoint.x
                this.point.y = futurePoint.y
            }
            battleContext.hitTheWall = !hitTheWall
        }
    }
}