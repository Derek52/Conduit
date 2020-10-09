package com.conduit.engine.gl

import com.conduit.engine.Node
import org.lwjgl.opengl.GL46.*


open class Mesh(var vertices: FloatArray, var indices: IntArray) : Node() {

    var vertexBuffer : VertexBuffer = VertexBuffer()
    var indexBuffer = IndexBuffer()

    constructor() : this(floatArrayOf(), intArrayOf())

    override fun init() {
        super.init()

        vertexBuffer.new()
        vertexBuffer.insert(vertices)
        indexBuffer.new()
        indexBuffer.insert(indices)
    }

    override fun update() {
        super.update()

        vertexBuffer.bind()
        indexBuffer.bind()
        glDrawElements(GL_TRIANGLES, indices.size, GL_UNSIGNED_INT, 0)
    }

    override fun dispose() {
        super.dispose()

        indexBuffer.clear()
        vertexBuffer.clear()
    }
}