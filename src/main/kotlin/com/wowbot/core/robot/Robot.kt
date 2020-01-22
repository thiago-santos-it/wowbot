package com.wowbot.core.robot

import com.wowbot.assets.image.TextureManager
import com.wowbot.assets.image.TextureManager.Image.*
import com.wowbot.core.engine.EngineContext
import com.wowbot.core.engine.GameObject

class Robot(val name: String, val nickname: String, val life: Int, private val index: Int): GameObject {

    private val textureManager = TextureManager()

    private var x: Float = 0f

    override fun render(context: EngineContext) {
        //MOCKUP

        if (index % 2 == 0) {
            context.batch.draw(
                textureManager.texture(TANK_BODY_1),
                context.screenWidth / 2 + x,
                context.screenHeight / 2
            )
            //TODO criar objeto cannon
            context.batch.draw(
                textureManager.texture(TANK_CANNON_1),
                context.screenWidth / 2 + 15 + x,
                context.screenHeight / 2 + 15
            )
            x += 2
        } else {
            context.batch.draw(
                textureManager.texture(TANK_BODY_2),
                context.screenWidth / 2 + x,
                context.screenHeight / 2
            )
            context.batch.draw(
                textureManager.texture(TANK_CANNON_2),
                context.screenWidth / 2 + 15 + x,
                context.screenHeight / 2 + 15
            )
            x -= 2
        }

        ///val action = Script.run(battleContext)
        // Interpretar ações...
        //if (action.cannon ... )...
    }
}