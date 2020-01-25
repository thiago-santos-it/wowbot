package com.wowbot.core.battle

data class BattleContext(var hitTheWall: Boolean) {
    fun toMap(): Map<String, Any> {
        return mapOf("hitTheWall" to hitTheWall)
    }
}