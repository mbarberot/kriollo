package kriollo.generator.templating

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.DirectoryGenerator

class JteDirectoryGenerator(val configuration: CodegenConfiguration): DirectoryGenerator() {
    override fun isActivated(configuration: CodegenConfiguration) = true

    override fun getDirectoryPath(): String {
        return configuration.templating.jte.sourceDirectory
    }
}