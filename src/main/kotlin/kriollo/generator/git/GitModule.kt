package kriollo.generator.git

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.generator.CodegenModule

class GitModule(configuration: CodegenConfiguration) : CodegenModule(configuration) {

    override fun provideGenerators(): List<Generator> = listOf(
        GitIgnoreFileGenerator(configuration)
    )

    override fun isActivated() = configuration.git.enabled
}