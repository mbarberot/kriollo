package kriollo.generator.kotlin

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.base.extensions.JavaDependencyExtension
import kriollo.generator.utils.ServiceProvider

class KotlinJavaDependenciesExtension(
    val configuration: CodegenConfiguration,
    serviceProvider: ServiceProvider
) : JavaDependencyExtension {

    override fun provideDependencies(): List<JavaArtifact> {
        return listOf(
            JavaArtifact("org.jetbrains.kotlin", "kotlin-stdlib", configuration.kotlin.version)
        )
    }

}