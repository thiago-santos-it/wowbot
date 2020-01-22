package com.wowbot.assets.image

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.wowbot.core.engine.AssetController

class TextureManager: AssetController {

    enum class Image(val file: String) {
        BACKGROUND("images/ground.png"),
        TANK_BODY_1("images/tank1.png"),
        TANK_BODY_2("images/tank2.png"),
        TANK_CANNON_1("images/cannon1.png"),
        TANK_CANNON_2("images/cannon2.png")
    }

    override fun load() {
        Image.values().forEach { image ->
            assetManager.load(image.file, Texture::class.java)
        }
        assetManager.finishLoading()
    }

    fun texture(image: Image): Texture {
        return texture(image.file)
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