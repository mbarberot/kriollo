package kriollo.generator.libs

import kriollo.generator.java.JavaArtifact
import kriollo.generator.java.JavaDependencyExtension
import kriollo.services.provider.ServiceProvider

class JacksonJavaDependenciesExtension(
    val serviceProvider: ServiceProvider,
) : JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        val (version, core) = serviceProvider.configuration.libs.jackson

        return buildList {
            if (serviceProvider.configuration.kotlin.enabled) {
                add(
                    JavaArtifact(
                        groupId = "com.fasterxml.jackson.module",
                        artifactId = "jackson-module-kotlin",
                        version = version
                    )
                )
            }

            core.forEach {
                add(
                    JavaArtifact(
                        groupId = "com.fasterxml.jackson.core",
                        artifactId = "jackson-$it",
                        version = version
                    )
                )
            }
        }
    }
}
