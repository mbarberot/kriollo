package kriollo.generator.libs

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.base.extensions.JavaDependenciesGeneratorExtensions

class JacksonJavaDependenciesExtension(val configuration: CodegenConfiguration) : JavaDependenciesGeneratorExtensions {
    override fun provideDependencies(): List<JavaArtifact> {
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
