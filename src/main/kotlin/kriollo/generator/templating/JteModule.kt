package kriollo.generator.templating

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.CodegenModule
import kriollo.generator.Generator

class JteModule(configuration: CodegenConfiguration) : CodegenModule(configuration) {
    override fun isActivated() = configuration.templating.jte.enabled

    override fun provideGenerators(): List<Generator> = listOf(
        JteDirectoryGenerator(configuration),
        JteRootFileGenerator(configuration),
    )

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(JteJavaDependenciesExtension(configuration))
    }
}

