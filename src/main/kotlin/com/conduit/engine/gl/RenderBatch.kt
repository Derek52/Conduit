package com.conduit.engine.gl

/***
 * Encapsulates a VBO and everything needed to render a batch
 * of vertices.
 */

class RenderBatch {
    var camera: Camera = Camera()
    lateinit var shader : Shader
    var vertexArrayObject = VertexArrayObject()
    var elementBufferObject = ElementBufferObject()
    constructor(){
        camera.init()
        vertexArrayObject.new()
        vertexArrayObject.bind()
        elementBufferObject.new()
    }

    fun update() {
        shader.bind()
        vertexArrayObject.bind()
        elementBufferObject.bind()

    }

    fun dispose() {
        shader.dispose()
        vertexArrayObject.clear()
        elementBufferObject.clear()
    }
}