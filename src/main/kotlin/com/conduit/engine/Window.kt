package com.conduit.engine

import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWWindowSizeCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL46
import org.lwjgl.system.MemoryUtil

class Window(private var width: Int, private var height: Int, private var title: String) {

    lateinit var root : Node
    var window: Long = 0

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
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE)



        window = glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL)
        if (window == MemoryUtil.NULL) throw RuntimeException("Failed to create the GLFW window")
        glfwMakeContextCurrent(window)
        glfwSwapInterval(1)
        glfwShowWindow(window)
    }



    private fun update() {
        GL.createCapabilities()
        root.init()
        glfwSetWindowSizeCallback(window, object : GLFWWindowSizeCallback(){
            override fun invoke(window: Long, width: Int, height: Int) {
                GL46.glViewport(0, 0, width, height)
            }

        })

        while (!glfwWindowShouldClose(window)) {
            GL20.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
            root.update()
            glfwSwapBuffers(window)
            glfwPollEvents()
        }
        root.dispose()
        GL.destroy()

    }
}