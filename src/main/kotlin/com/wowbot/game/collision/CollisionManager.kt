package com.wowbot.game.collision

object CollisionManager {

    private val listeners = mutableListOf<CollisionListener>()
    private val removeList = mutableListOf<CollisionListener>()

    fun register(listener: CollisionListener) {
        listeners.add(listener)
    }

    fun unregister(listener: CollisionListener) {
        removeList.add(listener)
    }

    fun detect() {
        var collide = false
        //TODO: Improve!
        listeners.forEach { objectA ->
            if (!collide) {

                listeners.forEach { objectB ->

                    val center = objectA.center() ?: objectB.center()
                    val rectangle = objectA.rectangle() ?: objectB.rectangle()

                    if (!collide && objectA.group() != objectB.group() && center != null && rectangle != null &&
                            center.x > rectangle.x && center.x < (rectangle.x + rectangle.width) &&
                            center.y > rectangle.y && center.y < (rectangle.y + rectangle.height)) {
                        objectA.collide()
                        objectB.collide()
                        collide = true
                    }
                }
            }
        }

        if (removeList.isNotEmpty()) {
            listeners.removeAll(removeList)
            removeList.clear()
        }
    }
}
//