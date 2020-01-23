package com.wowbot.core.arena

import com.badlogic.gdx.graphics.Texture
import com.wowbot.assets.image.TextureManager
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.wowbot.assets.standard.StdTexture
import com.wowbot.core.engine.EngineContext
import com.wowbot.core.engine.GameObject


class ArenaBackground: GameObject {

    private val textureManager = TextureManager()

    private var textureRegion: TextureRegion? = null

    override fun load() {
        textureManager.load()
        val texture = textureManager.texture(StdTexture.BACKGROUND)
        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat)
        textureRegion = TextureRegion(texture)
    }

    override fun render(context: EngineContext) {
        if (textureRegion != null) {
            textureRegion?.setRegion(0f, 0f, context.screenWidth, context.screenHeight)
            context.batch.draw(textureRegion, 0f, 0f)
        }
    }

    override fun dispose() {
        textureManager.dispose()
    }
}