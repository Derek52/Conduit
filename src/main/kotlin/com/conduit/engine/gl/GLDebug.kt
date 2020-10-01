package com.conduit.engine.gl

import org.lwjgl.opengl.GLDebugMessageCallbackI
import org.lwjgl.opengl.GL46.*
class GLDebug : GLDebugMessageCallbackI {

    override fun invoke(source: Int, type: Int, id : Int, severity: Int, length: Int, message: Long, userParam: Long) {


        GLError.errors.push(GLError("Callback: " + getDebugSource(source) + " " + getDebugType(type), severity))
    }

    private fun getDebugType(type: Int): String {
        when(type){
            GL_DEBUG_TYPE_ERROR -> return "GL_DEBUG_TYPE_ERROR"
            GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR -> return "GL_DEBUG_TYPE_DEPRECATED_BEHAVIOUR"
            GL_DEBUG_TYPE_PORTABILITY -> return "GL_DEBUG_TYPE_PORTABILITY"
            GL_DEBUG_TYPE_PERFORMANCE -> return "GL_DEBUG_TYPE_PERFORMANCE"
            GL_DEBUG_TYPE_MARKER -> return "GL_DEBUG_TYPE_MARKER"
            GL_DEBUG_TYPE_PUSH_GROUP -> return "GL_DEBUG_TYPE_PUSH_GROUP"
            GL_DEBUG_TYPE_POP_GROUP -> return "GL_DEBUG_TYPE_POP_GROUP"
            GL_DEBUG_TYPE_OTHER -> return "GL_DEBUG_TYPE_OTHER"

        }

        return ""
    }

    private fun getDebugSource(source : Int) : String {
        when (source) {
            GL_DEBUG_SOURCE_API -> return "GL_DEBUG_SOURCE_API"
            GL_DEBUG_SOURCE_WINDOW_SYSTEM -> return "GL_DEBUG_SOURCE_WINDOW_SYSTEM"
            GL_DEBUG_SOURCE_SHADER_COMPILER -> return "GL_DEBUG_SOURCE_SHADER_COMPILER"
            GL_DEBUG_SOURCE_THIRD_PARTY -> return "GL_DEBUG_SOURCE_THIRD_PARTY"
            GL_DEBUG_SOURCE_APPLICATION -> return "GL_DEBUG_SOURCE_APPLICATION"
            GL_DEBUG_SOURCE_OTHER -> return "GL_DEBUG_SOURCE_OTHER"
        }
        return ""
    }

}
