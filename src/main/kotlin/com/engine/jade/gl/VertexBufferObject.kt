package com.engine.jade.gl

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL40


class VertexBufferObject : BufferGL(){
    var id : Int = 0


    override fun new() {
        id = GL40.glGenBuffers()
        GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, id)
    }

    override fun insert(vertices: Any) {
        if(vertices is FloatArray) {
            val vertexData = BufferUtils.createFloatBuffer(vertices.size)
            vertexData.put(vertices)
            vertexData.flip()
            GL40.glBufferData(GL40.GL_ARRAY_BUFFER, vertexData, GL40.GL_DYNAMIC_DRAW)
        }
    }

    override fun bind() {
        GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, id)
    }
}
