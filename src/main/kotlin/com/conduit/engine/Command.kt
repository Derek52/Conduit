package com.conduit.engine

class Command(var id: Int, var data: String) {
    companion object {
        var commands = ArrayList<Command>()
    }
}