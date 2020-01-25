package com.wowbot.core.robot

import com.wowbot.core.engine.EngineContext
import com.wowbot.core.engine.GameObject

class Bullet(private val angle: Float, private val speedMultiplier: Int): GameObject {

    var done = false

    override fun render(context: EngineContext) {

    }

    companion object {
        const val MAX = 5
    }
}