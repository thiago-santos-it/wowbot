package com.wowbot.assets.image

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.wowbot.assets.standard.StdTexture
import com.wowbot.game.engine.AssetController

class TextureManager: AssetController {

    override fun load() {
        StdTexture.values().forEach { image ->
            assetManager.load(image.file, Texture::class.java)
        }
        assetManager.finishLoading()
    }

    fun texture(stdTexture: StdTexture): Texture {
        return texture(stdTexture.file)
    }

    fun texture(file: String): Texture {
        return assetManager.get<Texture>(file)
    }

    override fun dispose() {
        assetManager.dispose()
    }

    companion object {
        private val assetManager = AssetManager()
    }
}