package com.wowbot.game.robot

import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.game.robot.render.CannonRender
import org.lwjgl.util.Point

class Cannon(private val typeA: Boolean): GameObject {

    private var cannonRender: CannonRender? = null

    val bullets = mutableListOf<Bullet>()
    val amountOfBullets = 5

    var point: Point? = null

    override fun load() {
        cannonRender = CannonRender(typeA)
        cannonRender?.load()
    }

    fun rotateLeft() {
        cannonRender?.rotateLeft()
    }

    fun rotateRight() {
        cannonRender?.rotateRight()
    }

    fun fire(force: Int = 1) {
        val padding = 15
        val localPoint = point ?: return
        val clonedPoint = Point(
                localPoint.x + (cannonRender?.width() ?: 0) / 2 + padding ,
                localPoint.y + (cannonRender?.height() ?: 0) / 2 + padding)

        if (bullets.size < amountOfBullets) {
            bullets.add(Bullet(clonedPoint, cannonRender?.currentAngle() ?: 0f, force))
        }
    }

    fun fireHard() {
        repeat(amountOfBullets) { fire(2) }
    }

    fun currentAngle(): Float? {
        return cannonRender?.currentAngle()
    }

    override fun render(context: EngineContext) {
        cannonRender?.point = this.point
        cannonRender?.render(context)

        bullets.forEach { bullet ->
            bullet.render(context)
        }
        bullets.removeIf { bullet -> bullet.done }
    }
}