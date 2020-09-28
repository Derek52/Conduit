package com.conduit.engine.gl

import com.engine.jade.Node
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL46.*;
import org.lwjgl.stb.STBImage
import java.nio.ByteBuffer
import java.nio.IntBuffer

class Texture : Node {
    private var maxFilter: Int
    private var minFilter: Int
    private var path : String
    var texture : Int = 0
    lateinit var width : IntBuffer
    lateinit var height : IntBuffer
    lateinit var nrChannels : IntBuffer

    constructor(path : String, minFilter : Int, maxFilter : Int){
        this.path = path
        this.minFilter = minFilter
        this.maxFilter = maxFilter
    }

    override fun init() {
        super.init()

        val texture : ByteBuffer? = loadTexture()

        this.texture = glGenTextures()

        setTextureParams()

        glTexImage2D(
            GL_TEXTURE_2D,
            0,
            GL_RGBA,
            width.get(),
            height.get(),
            0,
            GL_RGBA,
            GL_UNSIGNED_BYTE,
            texture
        )
    }

    fun setTextureParams(){
        glActiveTexture(GL_TEXTURE0) // activate the texture unit first before binding texture
        glBindTexture(GL_TEXTURE_2D, texture)

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, minFilter)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, maxFilter)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE)
    }

    private fun loadTexture() : ByteBuffer? {
        width = BufferUtils.createIntBuffer(1);
        height = BufferUtils.createIntBuffer(1);
        nrChannels = BufferUtils.createIntBuffer(1);

        STBImage.stbi_set_flip_vertically_on_load(true)


        return STBImage.stbi_load(path, width, height, nrChannels, 4)
    }

    override fun update() {
        super.update()
        glBindTexture(GL_TEXTURE_2D, texture)

    }

    override fun dispose() {
        super.dispose()
    }
}