package com.conduit.engine.gl

import com.engine.jade.Node

class Mesh(vertices: FloatArray) : Node() {

    var vertexBufferObject : VertexBufferObject = VertexBufferObject()

    init {
        vertexBufferObject.new()
        vertexBufferObject.insert(vertices)
    }

    override fun update() {
        super.update()
        vertexBufferObject.bind()

    }
}