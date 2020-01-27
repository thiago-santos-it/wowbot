package com.wowbot.game.robot

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.wowbot.extensions.toRadians
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import org.lwjgl.util.Point
import kotlin.math.cos
import kotlin.math.sin

class Bullet(point: Point, private val angle: Float, private val speedMultiplier: Int): GameObject {

    var done = false

    private val shape = ShapeRenderer()
    private var elapsedSteps = 0

    private val hiddenSteps = 5
    private val stepsDuration = 100
    private val point = point

    private val stepSize = 10

    override fun render(context: EngineContext) {

        if (elapsedSteps > stepsDuration) {
            done = true
        } else {

            move()
            if (elapsedSteps > hiddenSteps) {
                val typeA = elapsedSteps % 2 == 1
                shape.color = if (typeA) { Color.RED } else { Color.ORANGE }

                context.batch.end()
                shape.begin(ShapeRenderer.ShapeType.Filled)
                shape.circle(point.x.toFloat(), point.y.toFloat(), if (typeA) { 4f } else { 6f })
                shape.end()
                context.batch.begin()
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