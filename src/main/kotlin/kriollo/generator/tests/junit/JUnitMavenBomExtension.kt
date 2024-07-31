package kriollo.generator.tests.junit

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.java.JavaArtifact
import kriollo.generator.maven.MavenBomExtension
import kriollo.generator.utils.ServiceProvider

class JUnitMavenBomExtension(
    val configuration: CodegenConfiguration,
    val serviceProvider: ServiceProvider
): MavenBomExtension {
    override fun provide(): List<JavaArtifact> {
        return listOf(
            JavaArtifact(
                groupId = "org.junit",
                artifactId = "junit-bom",
                version = configuration.tests.junit.junitVersion,
            )
        )
    }
}