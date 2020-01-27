package com.wowbot.game.robot.render

import com.wowbot.assets.image.RotatableTexture
import com.wowbot.assets.image.TextureManager
import com.wowbot.assets.standard.StdTexture
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import org.lwjgl.util.Point

class RobotRender(private val typeA: Boolean): GameObject {

    private val textureManager = TextureManager()
    private var rotatableTexture: RotatableTexture? = null

    var point: Point = Point(400, 400)

    override fun load() {
        val texture = textureManager.texture(if (typeA) { StdTexture.TANK_BODY_A } else { StdTexture.TANK_BODY_B })
        rotatableTexture = RotatableTexture(texture)
    }

    override fun render(context: EngineContext) {
        rotatableTexture?.draw(context.batch, point)
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

    fun width(): Int? {
        return rotatableTexture?.texture?.width
    }

    fun height(): Int? {
        return rotatableTexture?.texture?.height
    }
}