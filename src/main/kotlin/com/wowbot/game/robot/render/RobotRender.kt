package com.wowbot.game.robot.render

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Align
import com.wowbot.assets.font.FontManager
import com.wowbot.assets.image.RotatableTexture
import com.wowbot.assets.image.TextureManager
import com.wowbot.assets.standard.StdFont
import com.wowbot.assets.standard.StdTexture
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import org.lwjgl.util.Point
import org.lwjgl.util.Rectangle

class RobotRender(private val nickname: String, private val typeA: Boolean): GameObject {

    private val fontManager = FontManager(StdFont.ROBOT)
    private val textureManager = TextureManager()
    private var rotatableTexture: RotatableTexture? = null

    var point: Point? = null

    override fun load() {
        val texture = textureManager.texture(if (typeA) { StdTexture.TANK_BODY_A } else { StdTexture.TANK_BODY_B })
        rotatableTexture = RotatableTexture(texture, initialAngle = if (!typeA) { 180f } else { 0f })
    }

    override fun render(context: EngineContext) {
        val localPoint = point ?: return
        rotatableTexture?.draw(context.batch, localPoint)

        val font = fontManager.font(fontManager.size(5), Color.WHITE)
        font.draw(
                context.batch,
                nickname,
                localPoint.x + (rotatableTexture?.width()?: 0) / 2f,
                localPoint.y + (rotatableTexture?.height() ?: 0).toFloat() + 20, 0f, Align.center, false)

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