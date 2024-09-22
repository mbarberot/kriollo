package kriollo.generator.kotlin

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import kriollo.generator.CodegenModule
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v2024.0.0")
class KotlinModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = serviceProvider.configuration.kotlin.enabled

    override fun provideGenerators(): List<Generator> = listOf(
        KotlinSourceDirectoryGenerator(serviceProvider),
        KotlinTestDirectoryGenerator(serviceProvider),
    )

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(KotlinJavaDependenciesExtension(serviceProvider))
        generator.registerExtension(KotlinMavenPluginExtension(serviceProvider))
        generator.registerExtension(KotlinMavenPropertiesExtension(serviceProvider))
        generator.registerExtension(KotlinDefaultNixExtension(serviceProvider))
    }
}