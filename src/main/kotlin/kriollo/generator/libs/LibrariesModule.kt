package kriollo.generator.libs

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.CodegenModule
import kriollo.generator.Generator

class LibrariesModule(configuration: CodegenConfiguration) : CodegenModule(configuration) {
    override fun isActivated() = true

    override fun provideGenerators(): List<Generator> = listOf()

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(JacksonJavaDependenciesExtension(configuration))
    }

}