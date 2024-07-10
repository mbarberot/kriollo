package kriollo.generator.script.build

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.DirectoryGenerator

class BuildScriptDirectoryGenerator(configuration: CodegenConfiguration) : DirectoryGenerator() {
    override fun getDirectoryPath() = "./build"
}