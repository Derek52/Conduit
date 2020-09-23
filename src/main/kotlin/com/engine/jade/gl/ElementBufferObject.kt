package com.engine.jade.gl

import org.lwjgl.opengl.GL40
import java.nio.IntBuffer

class ElementBufferObject() : BufferGL(){
    var ebo : Int = 0;
    override fun new() {
        ebo = GL40.glGenBuffers();
        GL40.glBindBuffer(GL40.GL_ELEMENT_ARRAY_BUFFER, ebo);
        GL40.glBufferData(GL40.GL_ELEMENT_ARRAY_BUFFER, intArrayOf(0, 1, 3, 1, 2, 3), GL40.GL_DYNAMIC_DRAW);
    }

    override fun bind() {
        GL40.glBindBuffer(GL40.GL_ELEMENT_ARRAY_BUFFER, ebo)
    }

    override fun insert(data: Any) {
        if(data is IntBuffer)
            GL40.glBufferData(GL40.GL_ELEMENT_ARRAY_BUFFER, data, GL40.GL_DYNAMIC_DRAW)
    }

}