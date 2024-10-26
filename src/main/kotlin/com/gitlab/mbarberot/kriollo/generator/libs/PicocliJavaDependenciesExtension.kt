package com.gitlab.mbarberot.kriollo.generator.libs

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.java.JavaDependencyExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class PicocliJavaDependenciesExtension(
    val serviceProvider: ServiceProvider,
) : JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        val (enabled, version) = serviceProvider.configuration.project.libs.picocli

        if (!enabled) {
            return listOf()
        }

        return buildList {
            add(
                JavaArtifact(
                    groupId = "info.picocli",
                    artifactId = "picocli",
                    version = version,
                )
            )
        }
    }
}
