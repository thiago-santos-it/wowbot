package com.wowbot.assets.font

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.Color
import com.wowbot.assets.standard.StdFont
import com.wowbot.core.engine.AssetController


class FontManager: AssetController {

    private lateinit var fontFile: String

    constructor(font: StdFont) {
        fontFile = font.file
    }

    constructor(file: String) {
        fontFile = file
    }

    private val generator: FreeTypeFontGenerator by lazy {
        FreeTypeFontGenerator(Gdx.files.internal(fontFile))
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
}