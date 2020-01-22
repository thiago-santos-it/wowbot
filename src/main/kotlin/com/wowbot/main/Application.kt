package com.wowbot.main

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.wowbot.core.Game

fun main() {

      val config = LwjglApplicationConfiguration()
      config.title = "Wowbot Arena"
      config.width = Game.screen.screenWidth
      config.height = Game.screen.screenWidth
      config.useGL30 = false
      LwjglApplication(Game(), config)
}

