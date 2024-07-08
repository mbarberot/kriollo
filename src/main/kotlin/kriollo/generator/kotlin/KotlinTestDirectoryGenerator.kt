package kriollo.generator.kotlin

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.DirectoryGenerator

class KotlinTestDirectoryGenerator : DirectoryGenerator() {
    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.kotlin.enabled
    }

    override fun getDirectoryPath(): String {
        return "./src/test/kotlin"
    }
}