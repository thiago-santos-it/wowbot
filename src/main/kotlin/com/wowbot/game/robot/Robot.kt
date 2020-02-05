package com.wowbot.game.robot

import com.badlogic.gdx.Gdx
import com.wowbot.assets.standard.StdSound
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.extensions.toRadians
import com.wowbot.game.collision.CollisionListener
import com.wowbot.game.collision.CollisionManager
import com.wowbot.game.robot.context.RobotContext
import com.wowbot.game.robot.render.RobotRender
import com.wowbot.game.script.Script
import org.lwjgl.util.Point
import org.lwjgl.util.Rectangle
import java.util.UUID
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class Robot(private val script: Script): GameObject, CollisionListener {

    enum class Action(val damage: Float) {
        FORWARD(0.01f),
        BACKWARD(0.01f),
        LEFT(0.008f),
        RIGHT(0.008f),
        CANNON_LEFT(0f),
        CANNON_RIGHT(0f),
        FIRE(0.06f),
        FIRE_HARD(0.07f),
        NONE(0f)
    }

    private var cannon: Cannon? = null
    private var robotRender: RobotRender? = null
    private var currentAction: Action? = null

    private var point: Point? = null
    private var elapsedSteps: Float = 0f
    private var typeA: Boolean = false

    private val runScriptLimitInMillis = 200
    private val hitDamage = 10
    private val stepSize = 10
    private val actionStepsDuration = 1f
    private val collisionGroup = UUID.randomUUID().toString()

    var stop = false
    var hitTheWall = false
    var life = 100f
    var scriptParameters = mapOf<String, Any>()

    val name: String = script.inspect("name") ?: "No name"
    val nickname: String = script.inspect("nickname") ?: "No nickname"

    private var died = false
    private var collision = false

    fun context(): RobotContext {
        return RobotContext(
                hitTheWall = hitTheWall,
                life = life,
                x = point?.x ?: 0,
                y = point?.y ?: 0,
                robotAngle = robotRender?.currentAngle() ?: 0f,
                cannonAngle = cannon?.currentAngle() ?: 0f,
                runningBullets = cannon?.bullets?.size ?: 0,
                bulletsLimit = cannon?.amountOfBullets ?: 0,
                lastActionName = currentAction.toString())
    }

    fun load(typeA: Boolean) {

        this.typeA = typeA

        robotRender = RobotRender(nickname, typeA)
        cannon = Cannon(typeA, collisionGroup)
        cannon?.load()
        robotRender?.load()
        reset()

        CollisionManager.register(this)
    }

    fun reset() {
        life = 100f
        stop = false
        hitTheWall = false
        collision = false
        died = false
        point = null
        script.start()
    }

    override fun dispose() {
        CollisionManager.unregister(this)
    }

    override fun render(context: EngineContext) {

        randomPositionIfNeeded(context)
        performCollision(context)

        robotRender?.point = this.point
        robotRender?.render(context)

        if (life > 0) {
            cannon?.point = this.point
            cannon?.render(context)
        }

        if (!stop && elapsedSteps > actionStepsDuration) {
            elapsedSteps = 0f
            try {
                val time = System.currentTimeMillis()
                currentAction = Action.valueOf(script.run(scriptParameters) ?: "")
                life -= currentAction?.damage ?: 0f

                if (System.currentTimeMillis() - time > runScriptLimitInMillis) {
                    println("Timeout")
                    life -= hitDamage
                }
            } catch (e: Exception) {
                println("Invalid action")
                life -= 10
            }
        }

        if (!died && life <= 0) {
            died = true
            context.soundManager.play(StdSound.EXPLODE)
        }

        elapsedSteps++
        performAction(currentAction)
    }

    private fun performCollision(context: EngineContext) {
        if (collision) {
            collision = false
            life -= hitDamage
            context.soundManager.play(StdSound.HIT)
        }
    }

    private fun performAction(action: Action?) {
        if (action == null) { return }
        when (action) {
            Action.FORWARD -> move(1)
            Action.BACKWARD -> move(-1)
            Action.LEFT -> robotRender?.rotateLeft()
            Action.RIGHT -> robotRender?.rotateRight()
            Action.CANNON_LEFT -> cannon?.rotateLeft()
            Action.CANNON_RIGHT -> cannon?.rotateRight()
            Action.FIRE -> cannon?.fire()
            Action.FIRE_HARD -> cannon?.fireHard()
            Action.NONE -> {}
        }
    }

    override fun rectangle(): Rectangle? {
        val localPoint = point ?: return null
        val localHeight = robotRender?.height() ?: return null
        val localWidth = robotRender?.width() ?: return null

        return Rectangle(localPoint.x, localPoint.y, localWidth, localHeight)
    }

    override fun collide() {
        collision = true
        //Rollback
        when (currentAction) {
            Action.FORWARD -> move(-1)
            Action.BACKWARD -> move(1)
            Action.LEFT -> robotRender?.rotateRight()
            Action.RIGHT -> robotRender?.rotateLeft()
            else -> {}
        }
    }

    override fun group(): String {
        return collisionGroup
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
            this.hitTheWall = !hitTheWall
        }
    }

    private fun randomPositionIfNeeded(context: EngineContext) {
        if (this.point == null && this.robotRender != null) {
            val x = (context.screenWidth - 10 - (robotRender?.width() ?: 0)).toInt()
            val y = Random.nextInt(robotRender?.height() ?: 0, (context.screenHeight - (robotRender?.height() ?: 0)).toInt())
            this.point = Point(if (typeA) { 10 } else { x }, y)
        }
    }
}