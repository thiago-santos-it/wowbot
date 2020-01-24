package com.wowbot.main

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.wowbot.core.Game
import com.wowbot.core.script.ScriptLoader
import kotlin.system.exitProcess

fun main(args: Array<String>) {

      val config = LwjglApplicationConfiguration()
      config.title = "Wowbot Arena"
      config.width = Game.screen.screenWidth
      config.height = Game.screen.screenWidth
      config.useGL30 = false

      if (args.isEmpty()) {
            print("Please specify the script folder as program parameter!")
            exitProcess(0)
      }

      val scripts = ScriptLoader(args[0]).load()
      LwjglApplication(Game(scripts), config)
}

