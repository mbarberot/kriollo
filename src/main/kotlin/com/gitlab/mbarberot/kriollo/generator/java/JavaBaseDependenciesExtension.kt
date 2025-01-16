package com.gitlab.mbarberot.kriollo.generator.java

import com.gitlab.mbarberot.kriollo.HardcodedValues
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class JavaBaseDependenciesExtension(
    val serviceProvider: ServiceProvider
): JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        return listOf(
            JavaArtifact("org.jspecify", "jspecify", HardcodedValues.DEFAULT_JSPECIFY_VERSION)
        )
    }

}
