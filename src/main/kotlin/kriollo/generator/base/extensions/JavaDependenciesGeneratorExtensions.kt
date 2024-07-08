package kriollo.generator.base.extensions

import kriollo.configuration.JavaArtifact

fun interface JavaDependenciesGeneratorExtensions {
    fun provideDependencies(): List<JavaArtifact>
}