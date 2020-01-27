package com.wowbot.game.robot.render

import com.wowbot.assets.image.RotatableTexture
import com.wowbot.assets.image.TextureManager
import com.wowbot.assets.standard.StdTexture
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import org.lwjgl.util.Point

class CannonRender(private val typeA: Boolean): GameObject {

    private val textureManager = TextureManager()
    private var rotatableTexture: RotatableTexture? = null

    var point: Point = Point(400, 400)

    override fun load() {
        val texture = textureManager.texture(if (typeA) { StdTexture.TANK_CANNON_A } else { StdTexture.TANK_CANNON_B })
        val padding = 15
        val rotationStep = 10f
        rotatableTexture = RotatableTexture(texture, rotationStep, Point(padding, padding),-padding.toFloat())
    }

    fun rotateLeft() {
        rotatableTexture?.rotateLeft()
    }

    fun rotateRight() {
        rotatableTexture?.rotateRight()
    }

    fun currentAngle(): Float {
        return rotatableTexture?.currentAngle() ?: 0f
    }

    override fun render(context: EngineContext) {
        rotatableTexture?.draw(context.batch, point)
    }
}