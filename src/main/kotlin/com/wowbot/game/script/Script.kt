package com.wowbot.game.script

import javax.script.CompiledScript
import javax.script.ScriptContext
import javax.script.SimpleScriptContext

class Script(private val compiledScript: CompiledScript, private val scriptContext: ScriptContext = SimpleScriptContext()) {

    init {
        scriptContext.setBindings(compiledScript.engine.createBindings(), ScriptContext.ENGINE_SCOPE)
    }

    fun inspect(key: String): String? {
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