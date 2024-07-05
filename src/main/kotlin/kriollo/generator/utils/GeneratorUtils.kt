package kriollo.generator.utils

import java.io.File
import java.io.FileWriter
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.createParentDirectories
import kotlin.io.path.notExists

fun initFile(filename: String, template: String, isScript: Boolean = false) {
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
    fileWriter.write(template)
    fileWriter.flush()
    fileWriter.close()
}

fun createDirectories(vararg paths: String) {

    paths.forEach {
        println("> Generating directory $it")
    }

    paths
        .map { Path(it) }
        .filter { it.notExists() }
        .forEach {
            it.createParentDirectories()
            it.createDirectory()
        }

}