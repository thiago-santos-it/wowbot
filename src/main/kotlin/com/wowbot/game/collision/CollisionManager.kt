package com.wowbot.game.collision

object CollisionManager {

    private val listeners = mutableListOf<CollisionListener>()

    fun register(listener: CollisionListener) {
        listeners.add(listener)
    }

    fun detect() {

    }
}