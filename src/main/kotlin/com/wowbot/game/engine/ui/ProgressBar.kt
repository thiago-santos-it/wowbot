package com.wowbot.game.engine.ui

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable


class ProgressBar(width: Int, height: Int): ProgressBar(0f, 100f, 1f, false, ProgressBarStyle()) {

    init {
        style.background = coloredDrawable(width, height, Color.RED)
        style.knob = coloredDrawable(0, height, Color.GREEN)
        style.knobBefore = coloredDrawable(width, height, Color.GREEN)
        setWidth(width.toFloat())
        setHeight(height.toFloat())
    }

    fun coloredDrawable(width: Int, height: Int, color: Color?): Drawable? {
        val pixmap = Pixmap(width, height, Pixmap.Format.RGBA8888)
        pixmap.setColor(color)
        pixmap.fill()
        val drawable = TextureRegionDrawable(TextureRegion(Texture(pixmap)))
        pixmap.dispose()
        return drawable
    }
}