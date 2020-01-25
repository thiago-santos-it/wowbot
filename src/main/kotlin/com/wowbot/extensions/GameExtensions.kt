package com.wowbot.extensions

import com.badlogic.gdx.Game

fun Game.clearGL() {
    val backgroundColor = java.awt.Color.BLACK
    com.badlogic.gdx.Gdx.gl.glClearColor(
            backgroundColor.red / 255f,
            backgroundColor.green / 255f,
            backgroundColor.blue / 255f,
            backgroundColor.alpha / 255f
    )
    com.badlogic.gdx.Gdx.gl.glClear(org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT)
}
