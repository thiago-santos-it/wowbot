package com.wowbot.core.robot

import com.wowbot.assets.image.SpinableTexture
import com.wowbot.assets.image.TextureManager
import com.wowbot.assets.standard.StdTexture
import com.wowbot.core.engine.EngineContext
import com.wowbot.core.engine.GameObject
import org.lwjgl.util.Point

class Cannon(private val typeA: Boolean): GameObject {

    private val textureManager = TextureManager()
    private var spinableTexture: SpinableTexture? = null

    var point: Point = Point(0, 0)

    override fun load() {
        val texture = textureManager.texture(if (typeA) { StdTexture.TANK_CANNON_A } else { StdTexture.TANK_CANNON_B })
        val padding = 15
        val rotationStep = 10f
        spinableTexture = SpinableTexture(texture, rotationStep, Point(padding, padding),-padding.toFloat())
    }


    override fun render(context: EngineContext) {
        spinableTexture?.draw(context.batch, point)
        spinableTexture?.rotateRight()
    }
}