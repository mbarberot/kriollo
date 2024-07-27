package kriollo.generator.test

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.java.JavaArtifact
import kriollo.generator.java.JavaDependencyExtension
import kriollo.generator.utils.ServiceProvider

class JUnitJavaDependenciesExtension(
    val configuration: CodegenConfiguration,
    val serviceProvider: ServiceProvider,
) : JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        val junitVersion = configuration.tests.junitVersion
        val assertjVersion = configuration.tests.assertjVersion
        val junitCommonsVersion = configuration.tests.junitPlatformCommonsVersion
        return listOf(
            JavaArtifact("org.junit.jupiter", "junit-jupiter", junitVersion, "test"),
            JavaArtifact("org.junit.platform", "junit-platform-commons", junitCommonsVersion, "test"),
            JavaArtifact("org.assertj", "assertj-core", assertjVersion, "test"),
        )
    }
}
