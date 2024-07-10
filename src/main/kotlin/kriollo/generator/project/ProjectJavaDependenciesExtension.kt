package kriollo.generator.project

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.base.extensions.JavaDependencyExtension
import kriollo.generator.utils.ServiceProvider

class ProjectJavaDependenciesExtension(val configuration: CodegenConfiguration, serviceProvider: ServiceProvider) : JavaDependencyExtension {
    override fun provideDependencies(): List<JavaArtifact> {
        return configuration.project.dependencies
    }
}
