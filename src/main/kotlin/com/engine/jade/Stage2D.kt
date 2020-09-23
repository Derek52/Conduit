package com.engine.jade

import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL20
import org.lwjgl.system.MemoryUtil

class Stage2D {

    lateinit var root : Node2D;
    var window : Long = 0;
    private var title: String
    private var height: Int
    private var width: Int

    constructor(width: Int, height: Int, title: String){
        this.width = width;
        this.height = height;
        this.title = title;
    }

    fun show() {
        init()
        update()
        Callbacks.glfwFreeCallbacks(window)
        glfwDestroyWindow(window)
        glfwTerminate()
    }

    private fun init() {
        glfwInit()
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR,3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR,3)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);

        window = glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL)
        if (window == MemoryUtil.NULL) throw RuntimeException("Failed to create the GLFW window")
        glfwMakeContextCurrent(window)
        glfwSwapInterval(1)
        glfwShowWindow(window)
    }

    private fun update() {
        GL.createCapabilities()
        GL20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
        root.init()

        while (!glfwWindowShouldClose(window)) {
            GL20.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)

            root.update()

            glfwSwapBuffers(window)
            glfwPollEvents()
        }

        root.dispose()
    }
}