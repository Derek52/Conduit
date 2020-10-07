package com.conduit.engine.gl

import com.conduit.engine.Node


open class Mesh(vertices: FloatArray) : Node() {

    public lateinit var shader : Shader;
    var vertexBuffer : VertexBuffer = VertexBuffer()
    var elementBufferObject = IndexBuffer()


    init {
        vertexBuffer.new()
        vertexBuffer.insert(vertices)
        elementBufferObject.new()
        elementBufferObject.insert(intArrayOf(0, 1, 2, 2, 3, 0))

    }

    override fun update() {
        super.update()
        vertexBuffer.bind()
        elementBufferObject.bind()

    }

    override fun dispose() {
        super.dispose()
        elementBufferObject.clear()

    }
}