package com.gitlab.mbarberot.kriollo.services.filesystem

import java.io.IOException
import kotlin.io.path.Path
import kotlin.io.path.deleteIfExists
import kotlin.io.path.notExists
import kotlin.io.path.readLines

abstract class BaseFileSystemService : FileSystemService {

    private val generatedFiles: MutableList<GeneratedFile> = mutableListOf()

    abstract fun doCreateFile(filename: String, content: String, isScript: Boolean)

    override fun createFile(filename: String, content: String, isScript: Boolean) {
        doCreateFile(filename, content, isScript)
        generatedFiles.add(GeneratedFile(filename, content, isScript))
    }

    abstract fun doCreateDirectory(path: String)

    override fun createDirectory(path: String) {
        doCreateDirectory(path)
    }

    override fun generatedFiles(): List<GeneratedFile> {
        return generatedFiles
    }

    override fun removeFile(path: String) {
        println("> Deleting $path file")
        Path(path).deleteIfExists()
    }

    override fun getFileContent(path: String): List<String> {
        val file = Path(path)

        return if (file.notExists()) {
            throw IOException("File $path not found")
        } else {
            file.readLines(Charsets.UTF_8)
        }
    }

}
