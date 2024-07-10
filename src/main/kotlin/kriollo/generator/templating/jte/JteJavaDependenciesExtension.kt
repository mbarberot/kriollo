package kriollo.generator.templating.jte

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.base.extensions.JavaDependencyExtension
import kriollo.generator.utils.ServiceProvider

class JteJavaDependenciesExtension(val configuration: CodegenConfiguration, serviceProvider: ServiceProvider) :
    JavaDependencyExtension {
    override fun provideDependencies(): List<JavaArtifact> {
        val (_, groupId, artifactId, version, _, _) = configuration.templating.jte
        return buildList {
            add(JavaArtifact(groupId, artifactId, version))

            if (configuration.kotlin.enabled) {
                add(JavaArtifact(groupId, "jte-kotlin", version, "compile"))
            }
        }
    }

}