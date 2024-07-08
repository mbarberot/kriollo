package kriollo.generator.cli.script

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.DirectoryGenerator

class MainScriptDirectoryGenerator(val configuration: CodegenConfiguration): DirectoryGenerator() {
    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.cli.enabled && configuration.cli.script.enabled
    }

    override fun getDirectoryPath(): String {
        return configuration.cli.script.targetDirectory
    }
}