package kriollo.generator.templating

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.base.extensions.JavaDependenciesGeneratorExtensions

class JteJavaDependenciesExtension(val configuration: CodegenConfiguration) :
    JavaDependenciesGeneratorExtensions {
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