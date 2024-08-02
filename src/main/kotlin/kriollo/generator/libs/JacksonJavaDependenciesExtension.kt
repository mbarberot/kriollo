package kriollo.generator.libs

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.java.JavaArtifact
import kriollo.generator.java.JavaDependencyExtension
import kriollo.services.provider.ServiceProvider

class JacksonJavaDependenciesExtension(val configuration: CodegenConfiguration, serviceProvider: ServiceProvider) : JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        val (version, dataformats) = configuration.libs.jackson

        return buildList {
            if (configuration.kotlin.enabled) {
                add(
                    JavaArtifact(
                        groupId = "com.fasterxml.jackson.module",
                        artifactId = "jackson-module-kotlin",
                        version = version
                    )
                )
            }

            dataformats.forEach {
                when (it) {
                    "toml" -> add(
                        JavaArtifact(
                            groupId = "com.fasterxml.jackson.dataformat",
                            artifactId = "jackson-dataformat-toml",
                            version = version
                        )
                    )
                }
            }
        }
    }

}
