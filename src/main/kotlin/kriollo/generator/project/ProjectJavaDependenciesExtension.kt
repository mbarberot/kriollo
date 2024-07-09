package kriollo.generator.project

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.base.extensions.JavaDependencyExtension

class ProjectJavaDependenciesExtension(val configuration: CodegenConfiguration) : JavaDependencyExtension {
    override fun provideDependencies(): List<JavaArtifact> {
        return configuration.project.dependencies
    }
}
