package com.wowbot.main

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.wowbot.game.MainGame
import com.wowbot.game.script.ScriptLoader
import kotlin.system.exitProcess

fun main(args: Array<String>) {

      val config = LwjglApplicationConfiguration()
      config.title = "Wowbot Arena"
      config.width = 0
      config.height = 0
      config.resizable = false
      config.useGL30 = false

      if (args.isEmpty()) {
            print("Please specify the script folder as program parameter!")
            exitProcess(0)
      }

      val scripts = ScriptLoader(args[0]).load()
      LwjglApplication(MainGame(scripts), config)
}

