package com.wowbot.core.robot

import com.wowbot.assets.image.SpinableTexture
import com.wowbot.assets.image.TextureManager
import com.wowbot.assets.standard.StdTexture
import com.wowbot.core.engine.EngineContext
import com.wowbot.core.engine.GameObject
import org.lwjgl.util.Point

class Robot(val name: String, val nickname: String, val life: Int, private val typeA: Boolean): GameObject {

    private val textureManager = TextureManager()
    private var spinableTexture: SpinableTexture? = null

    private val cannon = Cannon(typeA)
    private var point = Point(400, 400)

    override fun load() {
        val texture = textureManager.texture(if (typeA) { StdTexture.TANK_BODY_A } else { StdTexture.TANK_BODY_B })
        spinableTexture = SpinableTexture(texture)
        cannon.load()
    }

    override fun render(context: EngineContext) {
        spinableTexture?.draw(context.batch, point)
        cannon.point = this.point
        cannon.render(context)

        ///val action = Script.run(battleContext)
        // Interpretar ações...
        //if (action.cannon ... )...
    }

    private fun forward() {
        //this.point.translate()
    }

    private fun backward() {
        //this.point.translate()
    }

    private fun rotateLeft() {
        spinableTexture?.rotateLeft()

    }

    private fun rotateRight() {
        spinableTexture?.rotateRight()
    }
}