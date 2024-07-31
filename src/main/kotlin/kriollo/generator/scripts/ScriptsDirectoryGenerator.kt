package kriollo.generator.scripts

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.DirectoryGenerator

class ScriptsDirectoryGenerator(val configuration: CodegenConfiguration) : DirectoryGenerator() {
    override fun getDirectoryPath() = "./${configuration.scripts.dir}"
}