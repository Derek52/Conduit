package com.conduit.engine.gl

import org.lwjgl.opengl.GL46
import java.util.*

open class GLError {
    companion object {
        var errors: Stack<GLError> = Stack<GLError>()

        fun list() {
            for (error in errors) {

                System.err.println("\n: GLError: " + formatError(error.severity) + ": " + error.desc + "\n")
            }
        }

        private fun formatError(severity: Int): String {
            when(severity){
                GL46.GL_DEBUG_SEVERITY_HIGH -> return("High")
                GL46.GL_DEBUG_SEVERITY_LOW -> return("Low")
                GL46.GL_DEBUG_SEVERITY_MEDIUM -> return("Medium")
                GL46.GL_DEBUG_SEVERITY_NOTIFICATION -> return("Notification")
            }
            return ""
        }
    }
    private var severity: Int
    var desc : String
    constructor(desc : String, severity : Int) {
        this.desc = desc
        this.severity = severity
    }


}