/**
 * WOW BOT SAMPLE SCRIPT
 *
 * Battle context fields at:
 *
 * com.wowbot.game.robot.BattleContext
 *
 * WARN: Please avoid accents in any text!!
 * PRO TIPS:
 *  Copy Battle Context class as a comment here!!
 *  Use print instead of console.log
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
var nickname = "Demolidor";
var name = "Jaum";

var Action = {
    FORWARD: "FORWARD", BACKWARD: "BACKWARD", LEFT: "LEFT", RIGHT: "RIGHT", CANNON_LEFT: "CANNON_LEFT", CANNON_RIGHT: "CANNON_RIGHT", FIRE: "FIRE"
};

function run(context) {
    return Action.FIRE;
}

var action = run(context);