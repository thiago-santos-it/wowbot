package com.wowbot.assets.sound

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.wowbot.core.engine.AssetController

class SoundManager: AssetController {

    enum class Effect(val file: String) {
        FIRE("sounds/synthetic_bomb.ogg"),
        EXPLODE("sounds/big_explosion.ogg"),
        RUN("sounds/moving.ogg"),
        HIT("sounds/bodyslam.ogg")
    }

    override fun load() {
        Effect.values().forEach { effect ->
            assetManager.load(effect.file, Sound::class.java)
        }
        assetManager.load(musicFile, Music::class.java)
        assetManager.finishLoading()
    }

    fun play(effect: Effect) {
        assetManager.get<Sound>(effect.file).play()
    }

    fun playMusic() {
        if (!playingMusic) {
            val music = assetManager.get<Music>(musicFile)
            music.isLooping = true
            music.volume = 1f
            music.play()
            playingMusic = music.isPlaying
        }
    }

    override fun dispose() {
        assetManager.dispose()
    }

    companion object {
        private val assetManager = AssetManager()
        private var playingMusic = false
        private const val musicFile = "sounds/music.mp3"
    }
}