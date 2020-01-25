package com.wowbot.game.script

import javax.script.CompiledScript
import javax.script.ScriptContext
import javax.script.SimpleScriptContext

class Script(private val compiledScript: CompiledScript, private val scriptContext: ScriptContext = SimpleScriptContext()) {

    fun inspect(key: String): String? {
        scriptContext.setAttribute("context", mapOf<String, Any>(), ScriptContext.ENGINE_SCOPE)
        compiledScript.eval(scriptContext)
        return scriptContext.getAttribute(key) as? String
    }

    fun run(runContext: Map<String, Any>): String? {
        return try {

            scriptContext.setAttribute("context", runContext, ScriptContext.ENGINE_SCOPE)
            compiledScript.eval(scriptContext) as? String

        } catch(e: Exception) {
            println("Operation ignored!!!")
            e.printStackTrace()
            null
        }

    }
}