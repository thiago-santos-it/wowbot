package com.wowbot.game.robot

import com.wowbot.assets.image.RotatableTexture
import com.wowbot.assets.image.TextureManager
import com.wowbot.assets.standard.StdTexture
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import org.lwjgl.util.Point

class Cannon: GameObject {

    private val textureManager = TextureManager()
    private val bullets = mutableListOf<Bullet>()
    private var rotatableTexture: RotatableTexture? = null

    var point: Point = Point(0, 0)

    fun load(typeA: Boolean) {
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

    fun fire(force: Int = 1) {
        if (bullets.size < Bullet.MAX) {
            bullets.add(Bullet(rotatableTexture?.currentAngle() ?: 0f, force))
        }
    }

    fun fireWithForce() {
        fire(3)
    }

    override fun render(context: EngineContext) {
        rotatableTexture?.draw(context.batch, point)
        bullets.forEach { bullet ->
            bullet.render(context)
        }
        bullets.removeIf { bullet -> bullet.done }
        rotatableTexture?.rotateRight()
    }
}