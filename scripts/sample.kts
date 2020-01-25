/**
 * WOW BOT SAMPLE SCRIPT
 *
 * Battle context fields at:
 *
 * com.wowbot.core.robot.BattleContext
 *
 * WARN: Please avoid accents in any text!!
 *
 * ACTIONS:
 * - FORWARD
 * - BACKWARD
 * - LEFT
 * - RIGHT
 * - CANNON_LEFT
 * - CANNON_RIGHT
 * - FIRE
 */
val nickname = "Cleberson"
val name = "Cleber"

enum class Action {
    FORWARD, BACKWARD, LEFT, RIGHT, CANNON_LEFT, CANNON_RIGHT, FIRE }

fun run(context: Map<String, Any>): Action {
    return Action.FORWARD
}

run(bindings["context"] as Map<String, Any>).toString()