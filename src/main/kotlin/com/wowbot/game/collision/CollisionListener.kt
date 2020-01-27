package com.wowbot.game.collision

import org.lwjgl.util.Point
import org.lwjgl.util.Rectangle


interface CollisionListener {

    fun center() : Point?

    fun rectangle(): Rectangle?

    fun collide()

}
