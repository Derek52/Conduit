package com.engine.jade.gl

abstract class BufferGL {
    abstract fun new()
    abstract fun insert(data : Any)
    abstract fun bind()
}