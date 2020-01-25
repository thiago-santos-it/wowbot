package com.wowbot.game.core.engine

interface GameObject: AssetController {

    override fun load() {}

    override fun dispose() {}

    fun render(context: EngineContext)
}