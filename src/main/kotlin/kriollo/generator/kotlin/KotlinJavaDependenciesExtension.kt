package kriollo.generator.kotlin

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.java.JavaArtifact
import kriollo.generator.java.JavaDependencyExtension
import kriollo.generator.utils.ServiceProvider

class KotlinJavaDependenciesExtension(
    val configuration: CodegenConfiguration,
    serviceProvider: ServiceProvider
) : JavaDependencyExtension {

    override fun provide(): List<JavaArtifact> {
        return listOf(
            JavaArtifact("org.jetbrains.kotlin", "kotlin-stdlib", configuration.kotlin.version)
        )
    }

}