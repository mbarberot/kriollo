package kriollo.generator.templating.jte

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.java.JavaArtifact
import kriollo.generator.java.JavaDependencyExtension
import kriollo.services.provider.ServiceProvider

class JteJavaDependenciesExtension(val configuration: CodegenConfiguration, serviceProvider: ServiceProvider) : JavaDependencyExtension {
    override fun provide(): List<JavaArtifact> {
        val (_, groupId, artifactId, version, _, _) = configuration.templating.jte
        return buildList {
            add(JavaArtifact(groupId, artifactId, version))

            if (configuration.kotlin.enabled) {
                add(JavaArtifact(groupId, "jte-kotlin", version, "compile"))
            }
        }
    }

}