package com.wowbot.game.robot.render

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject
import org.lwjgl.util.Point

class BulletRender: GameObject {

    private val shape = ShapeRenderer()
    private var elapsedSteps = 0

    var point: Point? = null

    override fun render(context: EngineContext) {
        val localPoint = point ?: return

        val typeA = elapsedSteps % 2 == 1
        shape.color = if (typeA) { Color.RED } else { Color.ORANGE }

        context.batch.end()
        shape.begin(ShapeRenderer.ShapeType.Filled)
        shape.circle(localPoint.x.toFloat(), localPoint.y.toFloat(), if (typeA) { 4f } else { 6f })
        shape.end()
        context.batch.begin()

        elapsedSteps++
    }
}