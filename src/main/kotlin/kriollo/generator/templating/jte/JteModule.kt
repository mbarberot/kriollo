package kriollo.generator.templating.jte

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.CodegenModule
import kriollo.generator.Generator
import kriollo.generator.utils.ServiceProvider

class JteModule(
    configuration: CodegenConfiguration,
    val serviceProvider: ServiceProvider,
) : CodegenModule(configuration) {

    override fun isActivated() = configuration.templating.jte.enabled

    override fun provideGenerators(): List<Generator> = listOf(
        JteDirectoryGenerator(configuration),
        JteRootFileGenerator(configuration),
    )

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(JteJavaDependenciesExtension(configuration))
        generator.registerExtension(JteMavenPluginExtension(configuration, serviceProvider))
    }

}