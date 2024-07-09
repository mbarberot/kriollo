package kriollo.generator.kotlin

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.base.extensions.JavaDependencyExtension

class KotlinJavaDependenciesExtension(
    val configuration: CodegenConfiguration
) : JavaDependencyExtension {

    override fun provideDependencies(): List<JavaArtifact> {
        return listOf(
            JavaArtifact("org.jetbrains.kotlin", "kotlin-stdlib", configuration.kotlin.version)
        )
    }

}