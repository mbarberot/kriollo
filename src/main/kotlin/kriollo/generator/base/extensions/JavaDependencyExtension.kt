package kriollo.generator.base.extensions

import kriollo.configuration.JavaArtifact

fun interface JavaDependencyExtension {
    fun provideDependencies(): List<JavaArtifact>
}