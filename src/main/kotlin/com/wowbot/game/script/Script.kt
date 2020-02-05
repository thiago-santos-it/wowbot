package com.wowbot.game.script

import javax.script.CompiledScript
import javax.script.ScriptContext
import javax.script.SimpleScriptContext

class Script(private val compiledScript: CompiledScript, private val scriptContext: ScriptContext = SimpleScriptContext()) {

    init {
        scriptContext.setBindings(compiledScript.engine.createBindings(), ScriptContext.ENGINE_SCOPE)
    }

    fun start() {
        scriptContext.setAttribute("first_run", true, ScriptContext.ENGINE_SCOPE)
        compiledScript.eval(scriptContext)
    }

    fun inspect(key: String): String? {
        scriptContext.setAttribute("first_run", false, ScriptContext.ENGINE_SCOPE)
        compiledScript.eval(scriptContext)
        return scriptContext.getAttribute(key) as? String
    }

    fun run(runContext: Map<String, Any>): String? {
        return try {
            scriptContext.setAttribute("first_run", false, ScriptContext.ENGINE_SCOPE)
            scriptContext.setAttribute("context", runContext, ScriptContext.ENGINE_SCOPE)
            compiledScript.eval(scriptContext) as? String
            scriptContext.getAttribute("action") as? String
        } catch(e: Exception) {
            println("Operation ignored!!!")
            e.printStackTrace()
            null
        }

    }
}