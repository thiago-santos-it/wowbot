package com.wowbot.game

import com.badlogic.gdx.Game
import com.wowbot.extensions.clearGL
import com.wowbot.extensions.default
import com.wowbot.game.engine.EngineContext
import com.wowbot.game.logic.ChampionshipManager
import com.wowbot.game.logic.SceneManager
import com.wowbot.game.robot.Robot
import com.wowbot.game.script.Script

class MainGame(private val scripts: List<Script>): Game() {

    private val championshipManager: ChampionshipManager by lazy {
        ChampionshipManager(scripts.map { script -> Robot(script) })
    }

    private val context: EngineContext by lazy {
        EngineContext()
    }

    private val sceneManager: SceneManager by lazy {
        SceneManager(this, context, championshipManager)
    }

    override fun render() {
        clearGL()
        context.batch.begin()
        super.render()
        context.batch.end()
    }

    override fun create() {
        context.load()
        championshipManager.load()
        context.soundManager.playMusic()
        context.camera.default()
        sceneManager.start()
    }

    override fun dispose() {
        context.dispose()
        championshipManager.dispose()
    }
}
