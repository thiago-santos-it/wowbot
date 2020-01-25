package com.wowbot.extensions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera

fun OrthographicCamera.default() {
    this.setToOrtho(
            false,
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat())
}