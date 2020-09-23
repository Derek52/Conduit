package com.engine.jade

import java.io.File
import java.nio.file.Files.*;
import java.nio.file.Paths

class Files {
    companion object {
        fun readFile(file: File): String {
            var lines: List<String> = readAllLines(Paths.get(file.absolutePath));
            var text: String = "";

            for (string in lines)
                text += string + "\n";

            return text;
        }
    }
}