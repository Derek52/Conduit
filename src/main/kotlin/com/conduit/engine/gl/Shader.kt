package com.conduit.engine.gl

import com.conduit.engine.Node
import org.joml.Matrix4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL43
import org.lwjgl.opengl.GL46.*;
import java.io.File
import java.nio.FloatBuffer
import java.nio.file.Files
import java.nio.file.Paths

class Shader(private var vertexShader: File, private var fragmentShader: File) : Node() {


    var shaderProgram : Int = 0
    var vertexShaderProgram : Int = 0
    var fragmentShaderProgram : Int = 0
    override fun init() {
        val vertexShaderSource = readFile(vertexShader)
        val fragmentShaderSource = readFile(fragmentShader)

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
        val program = glCreateShader(shaderType)
        glShaderSource(program, source)
        glCompileShader(program)

        return program
    }

    fun setUniformMatrix4f(uniform : String, transpose : Boolean, matrix : Matrix4f, size : Int){
        val floatBuffer : FloatBuffer = BufferUtils.createFloatBuffer(size)

        val uniformLocation = glGetUniformLocation(shaderProgram, uniform)

        glUniformMatrix4fv(uniformLocation, transpose, matrix.get(floatBuffer))

        floatBuffer.clear()
    }


    override fun update() {
        glUseProgram(shaderProgram)
    }

    private fun readFile(file: File): String {
        val lines: List<String> = Files.readAllLines(Paths.get(file.absolutePath))
        var text = ""

        for (string in lines)
            text += string + "\n"

        return text
    }

    override fun dispose() {
        glDeleteShader(vertexShaderProgram)
        glDeleteShader(fragmentShaderProgram)
    }

    fun vertexAttribLayout(location : Int, vertexGroupSize : Int, normalized : Boolean, stride : Int, pointer : Long) {
        glVertexAttribPointer(location, vertexGroupSize, GL_FLOAT, normalized, stride * 4, pointer * 4)

        glEnableVertexAttribArray(location)
    }

}