package com.wowbot.core

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import org.lwjgl.opengl.GL11
import java.awt.Color
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.wowbot.core.arena.ArenaBackground
import com.wowbot.core.arena.ArenaInformation
import com.wowbot.core.arena.ChampionshipManager
import com.wowbot.core.engine.EngineContext
import com.wowbot.core.robot.Robot
import com.wowbot.core.script.Script

class Game(private val scripts: List<Script>) : ApplicationListener {

    private var currentRobots: Pair<Robot, Robot>? = null

    private val championshipManager: ChampionshipManager by lazy {
        ChampionshipManager(scripts.map { script -> Robot(script) })
    }

    private val context: EngineContext by lazy {
        EngineContext()
    }

    private val arenaBackground: ArenaBackground by lazy {
        ArenaBackground()
    }

    private val arenaInformation = ArenaInformation()

    override fun render() {
        clearGL()
        context.batch.begin()
        arenaBackground.render(context)
        arenaInformation.render(context)
        currentRobots?.first?.render(context)
        currentRobots?.second?.render(context)
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
        championshipManager.load()

        setupBattle()
        setupScenario()
    }

    private fun setupScenario() {
        context.soundManager.playMusic()

        context.camera.setToOrtho(
                false,
                Gdx.graphics.width.toFloat(),
                Gdx.graphics.height.toFloat())
    }

    private fun setupBattle() {
        currentRobots = championshipManager.next()
        arenaInformation.currentRobots = currentRobots
        currentRobots?.first?.load(true)
        currentRobots?.second?.load(false)
    }

    override fun dispose() {
        context.dispose()
        currentRobots?.first?.dispose()
        currentRobots?.second?.dispose()
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
