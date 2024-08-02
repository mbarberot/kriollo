package kriollo.generator.cli

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.CodegenModule
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v0.1.0")
class CliModule(
    configuration: CodegenConfiguration,
    val serviceProvider: ServiceProvider,
) : CodegenModule(configuration) {

    override fun isActivated(): Boolean = configuration.cli.enabled

    override fun provideGenerators(): List<Generator> = listOf(
    )

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(CliMavenPluginExtension(configuration, serviceProvider))
    }
}