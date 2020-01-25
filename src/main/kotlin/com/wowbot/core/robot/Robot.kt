package com.wowbot.core.robot

import com.badlogic.gdx.Gdx
import com.wowbot.assets.image.SpinableTexture
import com.wowbot.assets.image.TextureManager
import com.wowbot.assets.standard.StdTexture
import com.wowbot.core.engine.EngineContext
import com.wowbot.core.engine.GameObject
import com.wowbot.core.extensions.toRadians
import com.wowbot.core.script.Script
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
        FIRE_WITH_FORCE
    }

    private val textureManager = TextureManager()
    private var spinableTexture: SpinableTexture? = null

    private val cannon = Cannon()
    private val battleContext = BattleContext(false)
    private val stepSize = 10
    private var point = Point(400, 400)

    var life = 100f
    val name: String = script.inspect("name") ?: "No name"
    val nickname: String = script.inspect("nickname") ?: "No nickname"

    fun load(typeA: Boolean) {
        val texture = textureManager.texture(if (typeA) { StdTexture.TANK_BODY_A } else { StdTexture.TANK_BODY_B })
        spinableTexture = SpinableTexture(texture)
        cannon.load()
    }

    override fun render(context: EngineContext) {
        spinableTexture?.draw(context.batch, point)
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
                Action.FIRE_WITH_FORCE -> cannon.fireWithForce()
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
        spinableTexture?.rotateLeft()

    }

    private fun rotateRight() {
        spinableTexture?.rotateRight()
    }

    private fun move(direction: Int) {
        val angle = spinableTexture?.currentAngle() ?: 0f
        if (spinableTexture != null) {

            val x = stepSize * cos(angle.toRadians())
            val y = stepSize * sin(angle.toRadians())

            val futurePoint = Point(this.point.x + direction * x.toInt(), this.point.y + direction * y.toInt())

            val width = Gdx.graphics.width - (spinableTexture?.texture?.width ?: 0)
            val height = Gdx.graphics.height - (spinableTexture?.texture?.height ?: 0)

            val hitTheWall = futurePoint.x > width || futurePoint.x < 0 || futurePoint.y < 0 || futurePoint.y > height
            if (!hitTheWall) {
                this.point.x = futurePoint.x
                this.point.y = futurePoint.y
            }
            battleContext.hitTheWall = !hitTheWall
        }
    }
}