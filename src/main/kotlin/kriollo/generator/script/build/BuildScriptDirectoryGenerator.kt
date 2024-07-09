package kriollo.generator.script.build

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.DirectoryGenerator

class BuildScriptDirectoryGenerator: DirectoryGenerator() {
    override fun getDirectoryPath() = "./build"

    override fun isActivated(configuration: CodegenConfiguration) = true

}