package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.maven.MavenArtifactExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class ProjectMavenArtifactExtension(
    val serviceProvider: ServiceProvider
): MavenArtifactExtension {

    override fun provide(): List<JavaArtifact> {
        val groupId = serviceProvider.configuration.project.groupId
        val name = serviceProvider.configuration.project.name
        val version = serviceProvider.configuration.project.version

        return listOf(
            JavaArtifact(groupId, name, version)
        )
    }

}
