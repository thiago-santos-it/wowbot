/**
 * WOW BOT SAMPLE SCRIPT
 *
 * WARN: Please avoid accents in any text!!
 * TIPS:
 * - Use print instead of console.log
 */
var nickname = "Jaum";
var name = "Script2";

var first_robot_run = first_run;

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
var opponentLife = context["opponentLife"];
var opponentUp = context["opponentUp"];
var opponentDown = context["opponentDown"];
var opponentRight = context["opponentRight"];
var opponentLeft = context["opponentLeft"];

var Action = {
    FORWARD: "FORWARD", BACKWARD: "BACKWARD", LEFT: "LEFT", RIGHT: "RIGHT", CANNON_LEFT: "CANNON_LEFT", CANNON_RIGHT: "CANNON_RIGHT", FIRE: "FIRE", FIRE_HARD: "FIRE_HARD", NONE: "NONE"
};

var memory = first_robot_run ? { count: 1, bla: "Bla" } : memory;

function run(context) {
    memory && (memory.count = memory.count + 1);
    print(JSON.stringify(memory));
    return Action.FIRE
}

var action = run(context);