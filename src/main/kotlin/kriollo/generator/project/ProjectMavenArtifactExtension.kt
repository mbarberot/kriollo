package kriollo.generator.project

import kriollo.generator.java.JavaArtifact
import kriollo.generator.maven.MavenArtifactExtension
import kriollo.services.provider.ServiceProvider

class ProjectMavenArtifactExtension(
    val serviceProvider: ServiceProvider
): MavenArtifactExtension {

    override fun provide(): List<JavaArtifact> {
        val (groupId, name, version) = serviceProvider.configuration.project
        return listOf(
            JavaArtifact(groupId, name, version)
        )
    }

}
