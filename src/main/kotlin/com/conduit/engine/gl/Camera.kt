package com.conduit.engine.gl

import com.engine.jade.Node
import org.joml.Matrix4f
import org.joml.Vector3f

class Camera : Node() {
    var view = Matrix4f()
    override fun init() {
        super.init()
        view.translate(Vector3f(0.0f, 0.0f, 0.0f))
    }

    override fun update() {
        super.update()
        println("Updating")
    }

    override fun dispose() {
        super.dispose()
    }
}