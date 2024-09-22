package kriollo.generator.templating.jte

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import kriollo.generator.CodegenModule
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v2024.1.0")
class JteModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = serviceProvider.configuration.templating.jte.enabled

    override fun provideGenerators(): List<Generator> = listOf(
        JteDirectoryGenerator(serviceProvider),
        JteRootFileGenerator(serviceProvider),
    )

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(JteJavaDependenciesExtension(serviceProvider))
        generator.registerExtension(JteMavenPluginExtension(serviceProvider))
    }
}