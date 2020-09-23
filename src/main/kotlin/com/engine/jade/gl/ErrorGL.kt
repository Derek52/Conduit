package com.engine.jade.gl

import org.lwjgl.opengl.GL40
import java.lang.IllegalStateException

class ErrorGL {
    companion object {
        fun error(errorInt : Int) : String {

            var error : String = "";
            when (errorInt) {
                GL40.GL_INVALID_ENUM -> error = "INVALID_ENUM"
                GL40.GL_INVALID_VALUE -> error = "INVALID_VALUE"
                GL40.GL_INVALID_OPERATION -> error = "INVALID_OPERATION"
                GL40.GL_STACK_OVERFLOW -> error = "STACK_OVERFLOW"
                GL40.GL_STACK_UNDERFLOW -> error = "STACK_UNDERFLOW"
                GL40.GL_OUT_OF_MEMORY -> error = "OUT_OF_MEMORY"
                GL40.GL_INVALID_FRAMEBUFFER_OPERATION -> error = "INVALID_FRAMEBUFFER_OPERATION"
            }
            //throw IllegalStateException(error)


            return error;
        }
    }
}