package kriollo.generator.tests.junit

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.java.JavaArtifact
import kriollo.generator.java.JavaDependencyExtension
import kriollo.generator.utils.ServiceProvider

class JUnitJavaDependenciesExtension(
    val configuration: CodegenConfiguration,
    val serviceProvider: ServiceProvider,
) : JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        val assertjVersion = configuration.tests.junit.assertjVersion
        return listOf(
            JavaArtifact(groupId = "org.junit.jupiter", artifactId = "junit-jupiter", scope = "test"),
            JavaArtifact(groupId = "org.junit.platform", artifactId = "junit-platform-commons", scope = "test"),
            JavaArtifact(
                groupId = "org.assertj",
                artifactId = "assertj-core",
                version = assertjVersion,
                scope = "test"
            ),
        )
    }
}