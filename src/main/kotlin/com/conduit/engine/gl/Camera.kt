package com.conduit.engine.gl

import com.conduit.engine.Node
import org.joml.Matrix4f

class Camera : Node() {
    var projection = Matrix4f()
    var view = Matrix4f()

    override fun init() {

    }
    override fun update() {
        super.update()
        projection.identity()
        view.identity()
        projection.ortho(0f, 300f,0f, 300f, -1.0f, 1.0f)
    }
}