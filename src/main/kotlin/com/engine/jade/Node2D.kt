package com.engine.jade

open class Node2D() {

    var children = ArrayList<Node2D>()
    var id : Int = 0;
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

    open fun dispose(){
        for(node in children)
            node.dispose()
    }

    private fun dispatchMessage() {
        Command.commands.add(Command(1, "Hello Node 1!"))
        Command.commands.add(Command(0, "Hello Node 0!"))

    }

    private fun checkMessages() {
        for(command in Command.commands){
            //if(command.id == id)
               // println("Message recieved by Node " + command.id)
        }
    }

}