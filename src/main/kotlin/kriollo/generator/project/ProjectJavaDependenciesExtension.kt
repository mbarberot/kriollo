package kriollo.generator.project

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.base.extensions.JavaDependenciesGeneratorExtensions

class ProjectJavaDependenciesExtension(val configuration: CodegenConfiguration) : JavaDependenciesGeneratorExtensions {
    override fun provideDependencies(): List<JavaArtifact> {
        return configuration.project.dependencies
    }
}
