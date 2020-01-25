package com.wowbot.game.robot

import com.badlogic.gdx.Gdx
import com.wowbot.assets.image.RotatableTexture
import com.wowbot.assets.image.TextureManager
import com.wowbot.assets.standard.StdTexture
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.extensions.toRadians
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

    private val textureManager = TextureManager()
    private var rotatableTexture: RotatableTexture? = null

    private val cannon = Cannon()
    private val battleContext = BattleContext(false)
    private val stepSize = 10
    private var point = Point(400, 400)

    var life = 100f
    val name: String = script.inspect("name") ?: "No name"
    val nickname: String = script.inspect("nickname") ?: "No nickname"

    fun load(typeA: Boolean) {
        val texture = textureManager.texture(if (typeA) { StdTexture.TANK_BODY_A } else { StdTexture.TANK_BODY_B })
        rotatableTexture = RotatableTexture(texture)
        cannon.load()
    }

    override fun render(context: EngineContext) {
        rotatableTexture?.draw(context.batch, point)
        cannon.point = this.point
        cannon.render(context)

        try {
            val actionName = script.run(battleContext.toMap()) ?: "${Action.NOTHING}"
            when (Action.valueOf(actionName)) {
                Action.FORWARD -> forward()
                Action.BACKWARD -> backward()
                Action.LEFT -> rotateLeft()
                Action.RIGHT -> rotateRight()
                Action.CANNON_LEFT -> cannon.rotateLeft()
                Action.CANNON_RIGHT -> cannon.rotateRight()
                Action.FIRE -> cannon.fire()
                Action.FIRE_HARD -> cannon.fireWithForce()
                Action.NOTHING -> {
                }
            }
        } catch(e: Exception) {
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
        rotatableTexture?.rotateLeft()

    }

    private fun rotateRight() {
        rotatableTexture?.rotateRight()
    }

    private fun move(direction: Int) {
        val angle = rotatableTexture?.currentAngle() ?: 0f
        if (rotatableTexture != null) {

            val x = stepSize * cos(angle.toRadians())
            val y = stepSize * sin(angle.toRadians())

            val futurePoint = Point(this.point.x + direction * x.toInt(), this.point.y + direction * y.toInt())

            val width = Gdx.graphics.width - (rotatableTexture?.texture?.width ?: 0)
            val height = Gdx.graphics.height - (rotatableTexture?.texture?.height ?: 0)

            val hitTheWall = futurePoint.x > width || futurePoint.x < 0 || futurePoint.y < 0 || futurePoint.y > height
            if (!hitTheWall) {
                this.point.x = futurePoint.x
                this.point.y = futurePoint.y
            }
            battleContext.hitTheWall = !hitTheWall
        }
    }
}