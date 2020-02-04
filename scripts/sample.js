/**
 * WOW BOT SAMPLE SCRIPT
 *
 * WARN: Please avoid accents in any text!!
 * TIPS:
 * - Use print instead of console.log
 */
var nickname = "Demolidor";
var name = "Jaum";

var life = context["life"];
var x = context["x"];
var y = context["y"];
var robotAngle = context["robotAngle"];
var cannonAngle = context["cannonAngle"];
var runningBullets = context["runningBullets"];
var bulletsLimit = context["bulletsLimit"];
var lastActionName = context["lastActionName"];
var hitTheWall = context["hitTheWall"];
var fieldWidth = context["fieldWidth"] ;
var fieldHeight = context["fieldHeight"];
var opponentDistance = context["opponentDistance"];
var opponentUp = context["opponentUp"];
var opponentDown = context["opponentDown"];
var opponentRight = context["opponentRight"];
var opponentLeft = context["opponentLeft"];

var Action = {
    FORWARD: "FORWARD", BACKWARD: "BACKWARD", LEFT: "LEFT", RIGHT: "RIGHT", CANNON_LEFT: "CANNON_LEFT", CANNON_RIGHT: "CANNON_RIGHT", FIRE: "FIRE", FIRE_HARD: "FIRE_HARD"
};

function run(context) {
    // print("Action: " + context);
    if (opponentDistance > 400) return Action.FORWARD;
    if (opponentDown && opponentLeft && cannonAngle == 180.0) return Action.CANNON_LEFT;
    if (opponentUp && opponentLeft && cannonAngle == 180.0) return Action.CANNON_RIGHT;
    if (opponentDown && opponentRight && cannonAngle == 0.0) return Action.CANNON_RIGHT;
    if (opponentUp && opponentRight && cannonAngle == 0.0) return Action.CANNON_LEFT;
    return Action.FIRE_HARD;
}

var action = run(context);