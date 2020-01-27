package com.wowbot.game.collision

object CollisionManager {

    private val listeners = mutableListOf<CollisionListener>()

    fun register(listener: CollisionListener) {
        listeners.add(listener)
    }

    fun detect() {
        val listeners = listeners.toTypedArray()
        var start = 0
        while (listeners.size - start > 1) {
            val objectA = listeners[start]

            for (index in start until listeners.size) {
                if (index != start) {
                    val objectB = listeners[index]
                    val objectACenter = objectA.center()
                    val objectBCenter = objectB.center()
                    if (objectACenter != null && objectBCenter != null &&
                            (objectB.rectangle()?.contains(objectA.center()) == true ||
                            objectA.rectangle()?.contains(objectB.center()) == true)) {
                        objectA.collide()
                        objectB.collide()
                    }
                }
            }
            start++
        }
    }
}