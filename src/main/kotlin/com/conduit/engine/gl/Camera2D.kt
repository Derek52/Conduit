package com.conduit.engine.gl

import com.engine.jade.Node
import org.joml.Matrix4f
import org.joml.Vector3f

class Camera2D : Node() {
    var view = Matrix4f()
    var projection = Matrix4f()
    var zoom : Float = 0.1f
    override fun init() {
        super.init()
        view.translate(Vector3f(0.0f, 0.0f, 0.0f))
    }

    override fun update() {
        super.update()
        projection.ortho(-(300.0f * zoom), 300.0f * zoom, -1.0f, 1.0f, -1.0f, 1.0f);
    }

    override fun dispose() {
        super.dispose()
    }
}