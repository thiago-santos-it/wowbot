package com.wowbot.core.engine

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wowbot.assets.sound.SoundManager
import com.wowbot.assets.text.FontManager

class EngineContext: AssetController {

    val soundManager: SoundManager by lazy {
        SoundManager()
    }

    val fontManager: FontManager by lazy {
        FontManager()
    }

    val batch: SpriteBatch by lazy {
        SpriteBatch()
    }

    val camera: OrthographicCamera by lazy {
        OrthographicCamera()
    }

    val screenWidth: Float by lazy {
        Gdx.graphics.width.toFloat()
    }

    val screenHeight: Float by lazy {
        Gdx.graphics.height.toFloat()
    }

    override fun load() {
        fontManager.load()
        soundManager.load()
    }

    override fun dispose() {
        fontManager.dispose()
        soundManager.dispose()
    }
}