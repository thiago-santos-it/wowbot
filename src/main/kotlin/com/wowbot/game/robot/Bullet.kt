package com.wowbot.game.robot

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.wowbot.extensions.toRadians
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import com.wowbot.game.robot.render.BulletRender
import org.lwjgl.util.Point
import kotlin.math.cos
import kotlin.math.sin

class Bullet(point: Point, private val angle: Float, private val speedMultiplier: Int): GameObject {

    var done = false

    private var elapsedSteps = 0

    private val bulletRender = BulletRender()
    private val hiddenSteps = 5
    private val stepsDuration = 100
    private val point = point

    private val stepSize = 10

    override fun render(context: EngineContext) {

        if (elapsedSteps > stepsDuration) {
            done = true
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
}