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
var nickname = "Demolidor";
var name = "Jaum";

var Action = {
    FORWARD: "FORWARD",
    BACKWARD: "BACKWARD",
    LEFT: "LEFT",
    RIGHT: "RIGHT",
    CANNON_LEFT: "CANNON_LEFT",
    CANNON_RIGHT: "CANNON_RIGHT",
    FIRE: "FIRE"
};

function run(context) {
    return Action.BACKWARD;
}