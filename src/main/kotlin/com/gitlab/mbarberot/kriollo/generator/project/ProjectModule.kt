package com.gitlab.mbarberot.kriollo.generator.project

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import com.gitlab.mbarberot.kriollo.generator.CodegenModule
import com.gitlab.mbarberot.kriollo.generator.Generator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v2024.3.0")
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