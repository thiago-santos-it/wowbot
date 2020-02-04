package com.wowbot.game.engine


//TODO: Improve this interface
interface GameObject: AssetController {

    override fun load() {}

    override fun dispose() {}

    fun render(context: EngineContext)
}