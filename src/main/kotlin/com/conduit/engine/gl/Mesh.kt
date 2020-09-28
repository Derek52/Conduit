package com.conduit.engine.gl

import com.engine.jade.Node

class Mesh : Node {

    var vertices : FloatArray;
    var vertexBufferObject : VertexBufferObject =
        VertexBufferObject()

    constructor(vertices: FloatArray){
        this.vertices = vertices
        vertexBufferObject.new()
        vertexBufferObject.insert(vertices)

    }

    override fun update() {
        super.update()
        vertexBufferObject.bind()
    }
}