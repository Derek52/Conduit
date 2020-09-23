package com.engine.jade.gl

import org.lwjgl.opengl.GL40
import java.io.File
import com.engine.jade.Files.Companion.readFile
import java.util.logging.Level
import java.util.logging.Logger

class Shader2D(vertexShader: File, fragmentShader: File) {


    var shaderProgram : Int = 0

    init {
        var vertexShaderSource = readFile(vertexShader)
        var fragmentShaderSource = readFile(fragmentShader)

        var vertexShaderProgram : Int = GL40.glCreateShader(GL40.GL_VERTEX_SHADER)
        GL40.glShaderSource(vertexShaderProgram, vertexShaderSource)
        GL40.glCompileShader(vertexShaderProgram)

        var fragmentShaderProgram : Int = GL40.glCreateShader(GL40.GL_FRAGMENT_SHADER)
        GL40.glShaderSource(fragmentShaderProgram, fragmentShaderSource)
        GL40.glCompileShader(fragmentShaderProgram)
        shaderProgram = GL40.glCreateProgram()

        GL40.glAttachShader(shaderProgram, vertexShaderProgram)
        GL40.glAttachShader(shaderProgram, fragmentShaderProgram)
        GL40.glLinkProgram(shaderProgram)

        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.WARNING, GL40.glGetShaderInfoLog(vertexShaderProgram))
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.WARNING, GL40.glGetShaderInfoLog(fragmentShaderProgram))




    }
    fun bind() {
        GL40.glUseProgram(shaderProgram)
    }

}