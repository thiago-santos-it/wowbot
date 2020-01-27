package com.wowbot.game.robot

import com.badlogic.gdx.Gdx
import com.wowbot.assets.sound.SoundManager
import com.wowbot.assets.standard.StdSound
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.extensions.toRadians
import com.wowbot.game.collision.CollisionListener
import com.wowbot.game.collision.CollisionManager
import com.wowbot.game.robot.context.BattleContext
import com.wowbot.game.robot.context.RobotContext
import com.wowbot.game.robot.render.RobotRender
import com.wowbot.game.script.Script
import org.lwjgl.util.Point
import org.lwjgl.util.Rectangle
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class Robot(private val script: Script): GameObject, CollisionListener {

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

    private val hitDamage = 10
    private val stepSize = 10
    private val actionStepsDuration = 10f

    var hitTheWall = false
    var life = 100f
    var scriptParameters = mapOf<String, Any>()

    val name: String = script.inspect("name") ?: "No name"
    val nickname: String = script.inspect("nickname") ?: "No nickname"

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
        cannon = Cannon(typeA)
        cannon?.load()
        robotRender?.load()

        CollisionManager.register(this)
    }

    override fun render(context: EngineContext) {

        val soundManager = context.soundManager

        randomPositionIfNeeded(context)
        performCollision(context)

        robotRender?.point = this.point
        robotRender?.render(context)

        if (life > 0) {
            cannon?.point = this.point
            cannon?.render(context)
        }

        if (elapsedSteps > actionStepsDuration) {
            elapsedSteps = 0f
            currentAction = script.run(scriptParameters) ?: "${Action.NOTHING}"
        }

        elapsedSteps++
        performAction(currentAction, soundManager)
    }

    private fun performCollision(context: EngineContext) {

        val soundManager = context.soundManager

        if (collision) {
            collision = false
            life -= hitDamage
            if (life <= 0) {
                soundManager.play(StdSound.EXPLODE)
            } else {
                soundManager.play(StdSound.HIT)
            }
        }
    }

    private fun performAction(action: String?, soundManager: SoundManager) {
        if (action == null) { return }
        try {
            when (Action.valueOf(action)) {
                Action.FORWARD -> { move(1); soundManager.play(StdSound.RUN) }
                Action.BACKWARD -> { move(-1); soundManager.play(StdSound.RUN) }
                Action.LEFT -> { robotRender?.rotateLeft(); soundManager.play(StdSound.RUN) }
                Action.RIGHT -> { robotRender?.rotateRight(); soundManager.play(StdSound.RUN) }
                Action.CANNON_LEFT -> { cannon?.rotateLeft(); soundManager.play(StdSound.RUN) }
                Action.CANNON_RIGHT -> { cannon?.rotateRight(); soundManager.play(StdSound.RUN) }
                Action.FIRE -> { cannon?.fire();  soundManager.play(StdSound.FIRE) }
                Action.FIRE_HARD -> { cannon?.fireHard(); soundManager.play(StdSound.FIRE) }
                Action.NOTHING -> {
                }
            }
        } catch (e: Exception) {
            print("Invalid action")
        }
    }

    override fun center(): Point? {
        val localPoint = point ?: return null
        val localHeight = robotRender?.height() ?: return null
        val localWidth = robotRender?.width() ?: return null

        return Point(localPoint.x + localWidth / 2, localPoint.y + localHeight)
    }

    override fun rect(): Rectangle? {
        val localPoint = point ?: return null
        val localHeight = robotRender?.height() ?: return null
        val localWidth = robotRender?.width() ?: return null

        return Rectangle(localPoint.x, localPoint.y, localPoint.x + localWidth, localPoint.y + localHeight)
    }

    override fun collide() {
        collision = true
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