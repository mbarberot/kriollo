package kriollo.generator.project

import kriollo.generator.java.JavaArtifact
import kriollo.generator.java.JavaDependencyExtension
import kriollo.services.provider.ServiceProvider

class ProjectJavaDependenciesExtension(
    val serviceProvider: ServiceProvider,
) :
    JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        return serviceProvider.configuration.project.dependencies
    }
}
