package com.wowbot.game.engine

interface GameObject: AssetController {

    override fun load() {}

    override fun dispose() {}

    fun render(context: EngineContext)
}