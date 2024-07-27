package kriollo.generator.kotlin

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.java.JavaArtifact
import kriollo.generator.java.JavaDependencyExtension
import kriollo.generator.utils.ServiceProvider

class KotlinJavaDependenciesExtension(
    val configuration: CodegenConfiguration,
    val serviceProvider: ServiceProvider,
) : JavaDependencyExtension {

    override fun provide(): List<JavaArtifact> {
        val kotlinVersion = configuration.kotlin.version
        return buildList {
            add(JavaArtifact("org.jetbrains.kotlin", "kotlin-stdlib", kotlinVersion))
            if (configuration.tests.enabled) {
                add(JavaArtifact("org.jetbrains.kotlin", "kotlin-test-junit5", kotlinVersion, "test"))
            }
        }
    }

}