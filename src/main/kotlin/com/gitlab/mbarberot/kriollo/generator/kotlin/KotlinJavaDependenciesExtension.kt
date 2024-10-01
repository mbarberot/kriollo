package com.gitlab.mbarberot.kriollo.generator.kotlin

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.java.JavaDependencyExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class KotlinJavaDependenciesExtension(
    val serviceProvider: ServiceProvider,
) : JavaDependencyExtension {

    override fun provide(): List<JavaArtifact> {
        val kotlinVersion = serviceProvider.configuration.kotlin.version
        return buildList {
            add(JavaArtifact("org.jetbrains.kotlin", "kotlin-stdlib", kotlinVersion))
            if (serviceProvider.configuration.tests.enabled) {
                add(JavaArtifact("org.jetbrains.kotlin", "kotlin-test-junit5", kotlinVersion, "test"))
            }
        }
    }

}