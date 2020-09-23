package com.engine.jade.gl

import com.engine.jade.Node2D
import org.lwjgl.opengl.GL40

class VertexArrayObject : Node2D(){
    var vao : Int = 0
    fun createAndBind() {
        vao = GL40.glGenVertexArrays()
        GL40.glBindVertexArray(vao)
    }

    fun bind() {
        GL40.glBindVertexArray(vao)
    }
}