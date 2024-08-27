package kriollo.generator.project

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import kriollo.generator.CodegenModule
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v2014.0.0")
class ProjectModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = true

    override fun provideGenerators(): List<Generator> = listOf(
        ProjectVersionGenerator(serviceProvider),
    )

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(ProjectJavaDependenciesExtension(serviceProvider))
        generator.registerExtension(ProjectMavenPropertiesExtension(serviceProvider))
        generator.registerExtension(ProjectMavenArtifactExtension(serviceProvider))
    }
}