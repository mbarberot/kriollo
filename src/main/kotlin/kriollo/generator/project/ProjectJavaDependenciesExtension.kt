package kriollo.generator.project

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.java.JavaArtifact
import kriollo.generator.java.JavaDependencyExtension
import kriollo.generator.utils.ServiceProvider

class ProjectJavaDependenciesExtension(val configuration: CodegenConfiguration, serviceProvider: ServiceProvider) :
    JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        return configuration.project.dependencies
    }
}
