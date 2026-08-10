package com.gitlab.mbarberot.kriollo.generator.tests.livingspecifications

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.java.JavaDependencyExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class ConcordionJavaDependencies(val serviceProvider: ServiceProvider) : JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        return listOf(
            JavaArtifact(
                groupId = "org.concordion",
                artifactId = "concordion",
                version = "4.0.1",
                scope = "test"
            )
        )
    }
}
