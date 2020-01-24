package com.wowbot.core.robot

import com.wowbot.assets.image.SpinableTexture
import com.wowbot.assets.image.TextureManager
import com.wowbot.assets.standard.StdTexture
import com.wowbot.core.engine.EngineContext
import com.wowbot.core.engine.GameObject
import com.wowbot.core.extensions.toRadians
import org.lwjgl.util.Point
import kotlin.math.cos
import kotlin.math.sin

class Robot(val name: String, val nickname: String, val life: Int, private val typeA: Boolean): GameObject {

    private val textureManager = TextureManager()
    private var spinableTexture: SpinableTexture? = null

    private val cannon = Cannon(typeA)
    private val stepSize = 10
    private var point = Point(400, 400)

    override fun load() {
        val texture = textureManager.texture(if (typeA) { StdTexture.TANK_BODY_A } else { StdTexture.TANK_BODY_B })
        spinableTexture = SpinableTexture(texture)
        cannon.load()
    }

    override fun render(context: EngineContext) {
        spinableTexture?.draw(context.batch, point)
        cannon.point = this.point
        cannon.render(context)
        forward()
        rotateLeft()

        ///val action = Script.run(battleContext)
        // Interpretar ações...
        //if (action.cannon ... )...
    }

    private fun forward() {
        move(1)
    }

    private fun backward() {
        move(-1)
    }

    private fun move(direction: Int) {
        val angle = spinableTexture?.currentAngle() ?: 0f
        if (spinableTexture != null) {
            val x = stepSize * cos(angle.toRadians())
            val y = stepSize * sin(angle.toRadians())
            this.point.translate(direction * x.toInt(), direction * y.toInt())
        }
    }

    private fun rotateLeft() {
        spinableTexture?.rotateLeft()

    }

    private fun rotateRight() {
        spinableTexture?.rotateRight()
    }
}