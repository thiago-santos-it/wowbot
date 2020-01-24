package com.wowbot.core

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import org.lwjgl.opengl.GL11
import java.awt.Color
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.wowbot.core.arena.ArenaBackground
import com.wowbot.core.arena.ArenaInformation
import com.wowbot.core.engine.EngineContext
import com.wowbot.core.robot.Robot
import com.wowbot.core.script.Script

class Game(private val scripts: List<Script>) : ApplicationListener {

    //TODO Logica de campeonato
    private val robots = Pair(
            Robot("Jaum", "Demolidor", 30, true),
            Robot("Cleber", "Clerberson", 100, false))

    private val context: EngineContext by lazy {
        EngineContext()
    }

    private val arenaBackground: ArenaBackground by lazy {
        ArenaBackground()
    }

    private val arenaInformation: ArenaInformation by lazy {
        ArenaInformation(robots)
    }

    override fun render() {
        clearGL()
        context.batch.begin()
        arenaBackground.render(context)
        arenaInformation.render(context)
        robots.first.render(context)
        robots.second.render(context)
        context.batch.end()
    }

    override fun pause() {
        //DO NOTHING
    }

    override fun resume() {
        //DO NOTHING
    }

    override fun resize(width: Int, height: Int) {
        //DO NOTHING
    }

    override fun create() {
        context.load()
        arenaBackground.load()
        arenaInformation.load()
        robots.first.load()
        robots.second.load()

        context.soundManager.playMusic()

        context.camera.setToOrtho(
            false,
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat())
    }

    override fun dispose() {
        context.dispose()
        robots.first.dispose()
        robots.second.dispose()
        arenaBackground.dispose()
        arenaInformation.dispose()

    }

    private fun clearGL() {
        val backgroundColor = Color.BLACK
        Gdx.gl.glClearColor(
            backgroundColor.red / 255f,
            backgroundColor.green / 255f,
            backgroundColor.blue / 255f,
            backgroundColor.alpha / 255f
        )
        Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT)
    }

    companion object {
        val screen = ScreenViewport()
    }
}
