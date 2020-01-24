package com.wowbot.core.script

import com.wowbot.core.robot.BattleContext
import javax.script.Bindings
import javax.script.ScriptEngine

class Script(private val engine: ScriptEngine, private val scope: Bindings) {

    fun run(context: BattleContext) {
        scope["context"] = context
        try {
            engine.eval("command = run(context)", scope)
            val command = scope["command"]
        } catch(e: Exception) {
            println("Operation ignored!!!")
        }
    }
}