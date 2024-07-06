package kriollo.generator.utils

import java.io.File
import java.io.FileWriter
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.createParentDirectories
import kotlin.io.path.notExists

class FilesytemService {
    fun createFile(filename: String, content: String = "", isScript: Boolean = false) {
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

    fun createDirectory(path: String) {
        println("> Generating directory $path")
        Path(path).let {
            if (it.notExists()) {
                it.createParentDirectories()
                it.createDirectory()
            }
        }
    }
}

