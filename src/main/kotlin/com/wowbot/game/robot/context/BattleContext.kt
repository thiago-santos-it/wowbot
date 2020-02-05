package com.wowbot.game.robot.context

data class BattleContext(
        var robotContext: RobotContext,
        var fieldWidth: Float,
        var fieldHeight: Float,
        val opponentLife: Float,
        val opponentDistance: Float,
        val opponentUp: Boolean,
        val opponentDown: Boolean,
        val opponentRight: Boolean,
        val opponentLeft: Boolean) {

    fun toMap(): Map<String, Any> {
        return mapOf(
                "life" to robotContext.life,
                "x" to robotContext.x,
                "y" to robotContext.y,
                "robotAngle" to robotContext.robotAngle,
                "cannonAngle" to robotContext.cannonAngle,
                "runningBullets" to robotContext.runningBullets,
                "bulletsLimit" to robotContext.bulletsLimit,
                "lastActionName" to robotContext.lastActionName,
                "hitTheWall" to robotContext.hitTheWall,
                "fieldWidth" to fieldWidth,
                "fieldHeight" to fieldHeight,
                "opponentDistance" to opponentDistance,
                "opponentUp" to opponentUp,
                "opponentDown" to opponentDown,
                "opponentRight" to opponentRight,
                "opponentLeft" to opponentLeft,
                "opponentLife" to opponentLife
        )
    }
}