package kriollo.generator.cli.script

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.generator.CodegenModule

class MainScriptModule(configuration: CodegenConfiguration) : CodegenModule(configuration) {

    override fun isActivated(): Boolean = configuration.cli.enabled && configuration.cli.script.enabled

    override fun provideGenerators(): List<Generator> = listOf(
        MainScriptDirectoryGenerator(configuration),
        MainScriptFileGenerator(configuration),
    )
}