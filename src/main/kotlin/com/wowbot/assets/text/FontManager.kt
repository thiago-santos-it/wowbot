package com.wowbot.assets.text

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.wowbot.core.engine.AssetController


class FontManager: AssetController {

    private val generator: FreeTypeFontGenerator by lazy {
        FreeTypeFontGenerator(Gdx.files.internal(FONT_FILE))
    }

    fun font(size: Int, color: Color = Color.WHITE): BitmapFont {
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = size
        parameter.color = color
        return generator.generateFont(parameter)
    }

    override fun load() {}

    override fun dispose() {
        generator.dispose()
    }

    companion object {
        const val FONT_FILE = "fonts/MandroidBB.ttf"
    }
}