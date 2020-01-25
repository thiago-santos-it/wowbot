package com.wowbot.assets.font

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.Color
import com.wowbot.assets.standard.StdFont
import com.wowbot.game.engine.AssetController


class FontManager: AssetController {

    private val cache = mutableMapOf<String, BitmapFont>()
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
        val key = "key-$size-${color.toIntBits()}"
        val cachedFont = cache.getOrDefault(key,null)
        return if (cachedFont != null) {
            cachedFont
        } else {
            val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
            parameter.size = size
            parameter.color = color
            val font = generator.generateFont(parameter)
            cache[key] = font
            return font
        }
    }

    override fun load() {}

    override fun dispose() {
        generator.dispose()
        cache.clear()
    }
}