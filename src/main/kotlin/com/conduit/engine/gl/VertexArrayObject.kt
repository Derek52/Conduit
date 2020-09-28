package com.conduit.engine.gl

import com.engine.jade.Node
import com.engine.jade.gl.Buffer
import org.lwjgl.opengl.GL46.*;

class VertexArrayObject : Buffer(){

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