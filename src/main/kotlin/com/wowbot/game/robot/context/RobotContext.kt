package com.wowbot.game.robot.context

data class RobotContext(val life: Float,
                        val x: Int,
                        val y: Int,
                        val robotAngle: Float,
                        val cannonAngle: Float,
                        val runningBullets: Int,
                        val bulletsLimit: Int,
                        val lastActionName: String,
                        val hitTheWall: Boolean)