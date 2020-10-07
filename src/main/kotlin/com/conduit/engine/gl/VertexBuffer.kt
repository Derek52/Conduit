package com.conduit.engine.gl

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL46.*;


class VertexBuffer : Buffer(){
    override fun new() {
        bufferID = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, bufferID)
    }

    override fun insert(data: Any) {
        if(data is FloatArray) {
            val vertexData = BufferUtils.createFloatBuffer(data.size)
            vertexData.put(data)
            vertexData.flip()
            glBufferData(GL_ARRAY_BUFFER, vertexData, GL_DYNAMIC_DRAW)
        }
    }
    override fun clear() {
        glDeleteBuffers(bufferID)
    }
    override fun bind() {
        glBindBuffer(GL_ARRAY_BUFFER, bufferID)
    }
}
