package com.conduit.engine.gl

import org.lwjgl.opengl.GL46.*

class IndexBuffer : Buffer(){
    override fun new() {
        bufferID = glGenBuffers()
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, bufferID)
    }

    override fun bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, bufferID)
    }

    override fun clear() {
        glDeleteBuffers(bufferID)
    }

    override fun insert(data: Any) {
        //bind()
        if(data is IntArray)
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_DYNAMIC_DRAW)
    }
}