package com.gitlab.mbarberot.kriollo.generator.templating.jte

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.java.JavaDependencyExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class JteJavaDependenciesExtension(
    val serviceProvider: ServiceProvider
) : JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        val (_, groupId, artifactId, version, _, _) = serviceProvider.configuration.project.libs.jte
        return buildList {
            add(JavaArtifact(groupId, artifactId, version))

            if (serviceProvider.configuration.kotlin.enabled) {
                add(JavaArtifact(groupId, "jte-kotlin", version, "compile"))
            }
        }
    }

}