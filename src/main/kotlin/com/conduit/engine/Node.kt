package com.conduit.engine

import java.util.*

open class Node {

    var children = ArrayList<Node>()
    open fun init(){
        for(node in children)
            node.update()
    }

    open fun update(){
        for(node in children)
            node.update()
        dispatchMessage()
        checkMessages()
    }

    fun add(node : Node){
        node.init()
        children.add(node)
    }
    open fun dispose(){
        for(node in children)
            node.dispose()
    }

    private fun dispatchMessage() {

    }

    private fun checkMessages() {
        /*for(command in Command.commands){
            if(command.id == id)
               //println("Message recieved by Node " + command.id)
        }*/
    }

}