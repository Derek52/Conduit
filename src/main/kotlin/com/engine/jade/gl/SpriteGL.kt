package com.engine.jade.gl

import com.engine.jade.Node2D
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL40.*;
import org.lwjgl.stb.STBImage
import java.nio.IntBuffer

class SpriteGL : Node2D {
    private var texture : Int = 0
    constructor(path: String?){

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_MIRRORED_REPEAT)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_MIRRORED_REPEAT)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)

        var width : IntBuffer = BufferUtils.createIntBuffer(1)
        var height : IntBuffer = BufferUtils.createIntBuffer(1)
        var nChannels : IntBuffer = BufferUtils.createIntBuffer(1)

        var image = STBImage.stbi_load(path, width, height, nChannels, 0)
        texture = glGenTextures()
        glBindTexture(GL_TEXTURE_2D, texture)
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width.get(), height.get(), 0, GL_RGB, GL_UNSIGNED_BYTE, image)

        STBImage.stbi_image_free(image)

    }
    override fun init() {
        super.init()
    }

    override fun update() {
        super.update()
    }

    override fun dispose() {
        super.dispose()
    }
}