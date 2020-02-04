/**
 * WOW BOT SAMPLE SCRIPT
 *
 * WARN: Please avoid accents in any text!!
 *
 * TIP: Use memory object to save data
 */

val memory = (bindings as javax.script.SimpleBindings)
val context = memory.getOrDefault("context", null) as? Map<String, Any>

memory["nickname"] = "Cleberson"
memory["name"] = "Cleber"

val life = context?.get("life") as? Float
val x = context?.get("x") as? Int
val y = context?.get("y") as? Int
val robotAngle = context?.get("robotAngle") as? Float
val cannonAngle = context?.get("cannonAngle") as? Float
val runningBullets = context?.get("runningBullets") as? Int
val bulletsLimit = context?.get("bulletsLimit") as? Int
val lastActionName = context?.get("lastActionName") as? String
val hitTheWall = context?.get("hitTheWall") as? Boolean
val fieldWidth = context?.get("fieldWidth") as? Float
val fieldHeight = context?.get("fieldHeight") as? Float
val opponentDistance = context?.get("opponentDistance") as? Float
val opponentUp = context?.get("opponentUp") as? Boolean
val opponentDown = context?.get("opponentDown") as? Boolean
val opponentRight = context?.get("opponentRight") as? Boolean
val opponentLeft = context?.get("opponentLeft") as? Boolean

enum class Action {
  FORWARD, BACKWARD, LEFT, RIGHT, CANNON_LEFT, CANNON_RIGHT, FIRE, FIRE_HARD, NONE
}

fun run(context: Map<String, Any>): Action {

    return Action.FORWARD
}

if (memory.containsKey("context")) {
    memory["action"] = run(memory["context"] as Map<String, Any>).toString()
}
