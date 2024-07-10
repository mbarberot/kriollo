package kriollo.generator.kotlin

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.DirectoryGenerator

class KotlinSourceDirectoryGenerator(configuration: CodegenConfiguration) : DirectoryGenerator() {

    override fun getDirectoryPath(): String {
        return "./src/main/kotlin"
    }
}