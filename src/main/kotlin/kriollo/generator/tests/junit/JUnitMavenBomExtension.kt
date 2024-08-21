package kriollo.generator.tests.junit

import kriollo.generator.java.JavaArtifact
import kriollo.generator.maven.MavenBomExtension
import kriollo.services.provider.ServiceProvider

class JUnitMavenBomExtension(
    val serviceProvider: ServiceProvider
): MavenBomExtension {
    override fun provide(): List<JavaArtifact> {
        return listOf(
            JavaArtifact(
                groupId = "org.junit",
                artifactId = "junit-bom",
                version = serviceProvider.configuration.tests.junit.junitVersion,
            )
        )
    }
}