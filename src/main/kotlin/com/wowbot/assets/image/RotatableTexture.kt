package com.wowbot.assets.image

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import org.lwjgl.util.Point

class RotatableTexture(val texture: Texture,
                       private val rotationStep: Float = 10f,
                       private val pointOffset: Point = Point(0, 0),
                       private val widthOffset: Float = 0f,
                       private val heightOffset: Float = 0f,
                       initialAngle: Float = 0f
) {

    private var angle: Float = initialAngle
    private val textureRegion = TextureRegion(texture, texture.width, texture.height)

    fun currentAngle(): Float {
        return angle
    }

    fun rotateRight() {
        angle -= rotationStep
        if (angle < 360) {
            angle += 360
        }
    }

    fun rotateLeft() {
        angle += rotationStep
        if (angle > 360) {
            angle += 360
        }
    }

    fun draw(batch: SpriteBatch, point: Point) {
        batch.draw(textureRegion,
                (point.x + pointOffset.x).toFloat(),
                (point.y + pointOffset.y).toFloat(),
                texture.width / 2 + widthOffset,
                texture.height / 2 + heightOffset,
                texture.width.toFloat(),
                texture.height.toFloat(),
                1f,
                1f,
                angle)
    }
}