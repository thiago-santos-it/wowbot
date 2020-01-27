package com.wowbot.game.robot

import com.wowbot.game.engine.EngineContext
import com.wowbot.game.engine.GameObject

class Bullet(private val angle: Float, private val speedMultiplier: Int): GameObject {

    var done = false

    override fun render(context: EngineContext) {

    }

    companion object {
        const val max = 5
    }
}