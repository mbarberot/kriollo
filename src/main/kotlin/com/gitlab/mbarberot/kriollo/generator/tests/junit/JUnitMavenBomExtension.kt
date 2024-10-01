package com.gitlab.mbarberot.kriollo.generator.tests.junit

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.maven.MavenBomExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

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