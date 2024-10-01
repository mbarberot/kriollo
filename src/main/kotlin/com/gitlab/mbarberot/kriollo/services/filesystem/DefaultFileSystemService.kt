package com.gitlab.mbarberot.kriollo.services.filesystem

import java.io.File
import java.io.FileWriter
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.createParentDirectories
import kotlin.io.path.notExists

class DefaultFileSystemService : BaseFileSystemService() {

    override fun doCreateFile(filename: String, content: String, isScript: Boolean) {
        val directoryPath = filename.substringBeforeLast("/", "")
        if (directoryPath.isNotEmpty()) {
            createDirectory(directoryPath)
        }

        println("> Generating $filename file")
        val fileToCreate = File(filename)
        if (!fileToCreate.exists()) {
            fileToCreate.createNewFile()
        }
        if (isScript) {
            val success = fileToCreate.setExecutable(true)
            if (!success) {
                println("! Could not make $filename executable")
            }
        }
        val fileWriter = FileWriter(fileToCreate)
        fileWriter.write(content)
        fileWriter.flush()
        fileWriter.close()

    }

    override fun doCreateDirectory(path: String) {
        Path(path).let {
            if (it.notExists()) {
                println("> Generating directory $path")
                it.createParentDirectories()
                it.createDirectory()
            }
        }
    }
}