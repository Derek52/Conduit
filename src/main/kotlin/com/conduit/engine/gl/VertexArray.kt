package com.conduit.engine.gl

import org.lwjgl.opengl.GL46.glBindVertexArray
import org.lwjgl.opengl.GL46.glGenVertexArrays

class VertexArray : Buffer(){

    override fun new(){
        bufferID = glGenVertexArrays()
        glBindVertexArray(bufferID)
    }

    override fun bind() {
        super.bind()
        glBindVertexArray(bufferID)

    }

    override fun insert(data: Any) {
        super.insert(data)
    }
}