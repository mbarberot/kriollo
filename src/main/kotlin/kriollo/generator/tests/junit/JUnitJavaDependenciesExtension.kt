package kriollo.generator.tests.junit

import kriollo.generator.java.JavaArtifact
import kriollo.generator.java.JavaDependencyExtension
import kriollo.services.provider.ServiceProvider

class JUnitJavaDependenciesExtension(
    val serviceProvider: ServiceProvider,
) : JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        val assertjVersion = serviceProvider.configuration.tests.junit.assertjVersion
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