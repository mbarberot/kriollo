package kriollo.generator.script.build

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.CodegenModule

class BuildScriptModule(configuration: CodegenConfiguration) : CodegenModule(configuration) {

    override fun isActivated() = configuration.scripts.build.enabled

    override fun provideGenerators() = listOf(
        BuildScriptDirectoryGenerator(),
        BuildScriptGenerator(configuration),
    )
}