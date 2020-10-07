package com.conduit.engine.gl

import com.conduit.engine.Node

class Batch : Node() {
    var vertexArrayObject = VertexArray()


    override fun init(){
        super.init()
        vertexArrayObject.new()
        vertexArrayObject.bind()
    }
    override fun update(){
        super.update()
        vertexArrayObject.bind()
    }
    override fun dispose(){
        super.dispose()
        vertexArrayObject.clear()
    }
}