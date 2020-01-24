package com.wowbot.core.script

import java.io.File
import javax.script.ScriptEngineManager
import javax.script.ScriptContext
import javax.script.SimpleScriptContext

class ScriptLoader(private val scriptsDirectory: String) {

    fun load(): List<Script> {
        val scripts = mutableListOf<Script>()
        val manager = ScriptEngineManager()
        val javascriptEngine = manager.getEngineByExtension("js")
        val kotlinEngine = manager.getEngineByExtension("kts")

        File(scriptsDirectory).walk().forEach { file ->
            try {
                val engineScope = SimpleScriptContext().getBindings(ScriptContext.ENGINE_SCOPE)

                if (file.name.endsWith("js")) {
                    kotlinEngine.eval("var command = \"none\"", engineScope)
                    javascriptEngine.eval(file.bufferedReader(), engineScope)
                    scripts.add(Script(javascriptEngine, engineScope))
                }

                if (file.name.endsWith("kts")) {
                    kotlinEngine.eval("import com.wowbot.core.robot.BattleContext", engineScope)
                    kotlinEngine.eval("var command: String = \"none\"", engineScope)
                    kotlinEngine.eval(file.bufferedReader(), engineScope)
                    scripts.add(Script(kotlinEngine, engineScope))
                }
            } catch(e: Exception) {
                println("Somebody lose: ${file.name} - ${e.message}")
                e.printStackTrace()
            }
        }
        return scripts
    }
}