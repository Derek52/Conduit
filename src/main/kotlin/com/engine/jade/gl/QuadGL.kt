package com.engine.jade.gl

import com.engine.jade.Node2D
import org.lwjgl.opengl.GL40

class QuadGL : Node2D {

    var vertices : FloatArray;
    var vertexBufferObject : VertexBufferObject = VertexBufferObject()

    constructor(vertices: FloatArray){
        this.vertices = vertices;
        vertexBufferObject.new()
        vertexBufferObject.insert(vertices)

    }

    override fun init() {
        super.init()

    }

    override fun update() {
        super.update()
        GL40.glEnableVertexAttribArray(0)


        GL40.glDrawElements(GL40.GL_TRIANGLES, 6, GL40.GL_UNSIGNED_INT, 0);

    }

    override fun dispose() {
        super.dispose()
    }
}