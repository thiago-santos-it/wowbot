package com.wowbot.game.collision

import org.lwjgl.util.Point
import org.lwjgl.util.Rectangle

//TODO: Improve this interface
interface CollisionListener {

    fun center() : Point? {
        return null
    }

    fun rectangle(): Rectangle? {
        return null
    }

    fun collide()

    fun group(): String
}
