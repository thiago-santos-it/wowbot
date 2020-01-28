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
        val listenersArray = listeners.toTypedArray()
        var start = 0
        while (listenersArray.size - start > 1) {
            val objectA = listenersArray[start]

            for (index in start until listenersArray.size) {

                val objectB = listenersArray[index]

                if (index != start && objectA.group() != objectB.group()) {

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
        if (removeList.isNotEmpty()) {
            listeners.removeAll(removeList)
        }
    }
}