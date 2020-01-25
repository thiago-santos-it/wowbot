package com.wowbot.game.script

import java.io.File
import javax.script.ScriptEngineManager
import javax.script.Compilable

class ScriptLoader(private val scriptsDirectory: String) {

    fun load(): List<Script> {
        val scripts = mutableListOf<Script>()
        val manager = ScriptEngineManager()

        File(scriptsDirectory).walk().forEach { file ->
            try {
                if (file.name.endsWith("js")) {
                    val javascriptEngine = manager.getEngineByExtension("js") as Compilable
                    scripts.add(Script(javascriptEngine.compile(file.readText(Charsets.UTF_8))))
                }

                if (file.name.endsWith("kts")) {
                    val kotlinEngine = manager.getEngineByExtension("kts") as Compilable
                    scripts.add(Script(kotlinEngine.compile(file.readText(Charsets.UTF_8))))
                }
            } catch(e: Exception) {
                println("Somebody lose: ${file.name} - ${e.message}")
                e.printStackTrace()
            }
        }
        return scripts
    }
}