/**
 * WOW BOT SAMPLE SCRIPT
 *
 * Battle context fields at:
 *
 * com.wowbot.game.robot.BattleContext
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
 * - FIRE_HARD
 */

val memory = (bindings as javax.script.SimpleBindings)

memory["nickname"] = "Cleberson"
memory["name"] = "Cleber"

enum class Action {
  FORWARD, BACKWARD, LEFT, RIGHT, CANNON_LEFT, CANNON_RIGHT, FIRE
}

fun run(context: Map<String, Any>): Action {
    return Action.FORWARD
}

if (memory.containsKey("context")) {
    run(memory["context"] as Map<String, Any>).toString()
}