package com.conduit.engine.gl

import org.joml.Matrix4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL43
import org.lwjgl.opengl.GL46
import org.lwjgl.opengl.GL46.*;
import java.io.File
import java.nio.FloatBuffer
import java.nio.file.Files
import java.nio.file.Paths
import java.util.logging.Level
import java.util.logging.Logger

class Shader(vertexShader: File, fragmentShader: File) {


    var shaderProgram : Int = 0
    var vertexShaderProgram : Int = 0
    var fragmentShaderProgram : Int = 0
    init {
        var vertexShaderSource = readFile(vertexShader)
        var fragmentShaderSource = readFile(fragmentShader)

        vertexShaderProgram = createShader(GL_VERTEX_SHADER, vertexShaderSource)


        fragmentShaderProgram = createShader(GL_FRAGMENT_SHADER, fragmentShaderSource)

        createProgram(vertexShaderProgram, fragmentShaderProgram)

        val vertexShaderLog : String =  glGetShaderInfoLog(vertexShaderProgram)
        val fragmentShaderLog : String = glGetShaderInfoLog(fragmentShaderProgram)

        if(!(vertexShaderLog.isEmpty() && fragmentShaderLog.isEmpty())){
            GLError.errors.push(GLError(vertexShaderLog, GL43.GL_DEBUG_SEVERITY_MEDIUM))
        }
    }

    private fun createProgram(vertexShaderProgram : Int, fragmentShaderProgram : Int){
        shaderProgram = glCreateProgram()

        glAttachShader(shaderProgram, vertexShaderProgram)
        glAttachShader(shaderProgram, fragmentShaderProgram)
        glLinkProgram(shaderProgram)
    }

    private fun createShader(shaderType : Int, source : String) : Int{
        var program = glCreateShader(shaderType)
        glShaderSource(program, source)
        glCompileShader(program)

        return program
    }

    fun setUniformMatrix4f(uName : String, transpose : Boolean , matrix : Matrix4f, size : Int){
        var floatBuffer : FloatBuffer = BufferUtils.createFloatBuffer(size)

        var uniformLocation = glGetUniformLocation(shaderProgram, uName)

        glUniformMatrix4fv(uniformLocation, transpose, matrix.get(floatBuffer))

        floatBuffer.clear()
    }


    fun bind() {
        glUseProgram(shaderProgram)

    }

    private fun readFile(file: File): String {
        var lines: List<String> = Files.readAllLines(Paths.get(file.absolutePath));
        var text = "";

        for (string in lines)
            text += string + "\n";

        return text;
    }

    fun dispose() {
        glDeleteShader(vertexShaderProgram)
        glDeleteShader(fragmentShaderProgram)
    }

    fun vertexAttribLayout(location : Int, vertexGroupSize : Int, normalized : Boolean, stride : Int, pointer : Long) {
        glVertexAttribPointer(location, vertexGroupSize, GL_FLOAT, normalized, stride * 4, pointer * 4)

        glEnableVertexAttribArray(location)
    }

}