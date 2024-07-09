package kriollo.generator.templating.jte

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.DirectoryGenerator

class JteDirectoryGenerator(val configuration: CodegenConfiguration): DirectoryGenerator() {

    override fun getDirectoryPath(): String {
        return configuration.templating.jte.sourceDirectory
    }
}