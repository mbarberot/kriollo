package com.gitlab.mbarberot.kriollo.generator.libs

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.java.JavaDependencyExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class FeignJavaDependenciesExtension(
    val serviceProvider: ServiceProvider,
) : JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        val (enabled, version) = serviceProvider.configuration.project.libs.feign
        val jacksonEnabled = serviceProvider.configuration.project.libs.jackson.enabled

        if (!enabled) {
            return listOf()
        }

        val groupId = "io.github.openfeign"

        return buildList {
            add(JavaArtifact(groupId = groupId, artifactId = "feign-core", version = version))

            if(jacksonEnabled) {
                add(JavaArtifact(groupId = groupId, artifactId = "feign-jackson", version = version))
            }
        }
    }
}
