package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.java.JavaDependencyExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class ProjectJavaDependenciesExtension(
    val serviceProvider: ServiceProvider,
) :
    JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        return serviceProvider.configuration.project.dependencies
    }
}
