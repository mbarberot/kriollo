package com.gitlab.mbarberot.kriollo.generator.project

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import com.gitlab.mbarberot.kriollo.generator.CodegenModule
import com.gitlab.mbarberot.kriollo.generator.Generator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.kriollo.Generated

@Generated("Generated by Kriollo")
class ProjectModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = true

    override fun provideGenerators(): List<Generator> = listOf(
        ProjectVersionGenerator(serviceProvider),
        GeneratedAnnotationGenerator(serviceProvider),
    )

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(ProjectJavaDependenciesExtension(serviceProvider))
        generator.registerExtension(ProjectMavenPropertiesExtension(serviceProvider))
        generator.registerExtension(ProjectMavenArtifactExtension(serviceProvider))
        generator.registerExtension(ProjectGitIgnoreExtension(serviceProvider))
    }
}