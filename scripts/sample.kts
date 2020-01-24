/**
 * WOW BOT SAMPLE SCRIPT
 *
 * Battle context fields at:
 *
 * com.wowbot.core.robot.BattleContext
 *
 * WARN: Please avoid accents in any text!!
 * PRO TIP: Copy Battle Context class as a comment here!!
 */
val nickname = "Cleberson"
val name = "Cleber"

enum class Action {
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT,
    CANNON_LEFT,
    CANNON_RIGHT,
    FIRE
}

fun run(context: BattleContext): String {
    return Action.FORWARD.toString()
}