package com.wowbot.game.robot

import com.wowbot.assets.standard.StdSound
import com.wowbot.extensions.toRadians
import com.wowbot.game.collision.CollisionListener
import com.wowbot.game.collision.CollisionManager
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.game.robot.render.BulletRender
import org.lwjgl.util.Point
import org.lwjgl.util.Rectangle
import kotlin.math.cos
import kotlin.math.sin

class Bullet(point: Point,
             private val angle: Float,
             private val speedMultiplier: Int,
             private val collisionGroup: String): GameObject, CollisionListener {

    var done = false

    private var elapsedSteps = 0

    private val bulletRender = BulletRender()
    private val hiddenSteps = 4
    private val stepsDuration = 60
    private val point = point

    private val stepSize = 10
    private var playSound = true

    init {
        CollisionManager.register(this)
    }

    override fun render(context: EngineContext) {
        if (done) { return }

        if (playSound) {
            context.soundManager.play(StdSound.FIRE)
            playSound = false
        }

        if (elapsedSteps > stepsDuration) {
            die()
        } else {
            move()
            bulletRender.point = point
            if (elapsedSteps > hiddenSteps) {
                bulletRender.render(context)
            }
        }
        elapsedSteps++
    }

    private fun move() {
        val x = stepSize * speedMultiplier * cos(angle.toRadians())
        val y = stepSize * speedMultiplier * sin(angle.toRadians())
        point.translate( x.toInt(), y.toInt())
    }

    override fun center(): Point? {
        return if (elapsedSteps > hiddenSteps) {
            return point
        } else {
            null
        }
    }

    override fun group(): String {
        return collisionGroup
    }

    override fun collide() {
        die()
    }

    private fun die() {
        done = true
        CollisionManager.unregister(this)
    }
}