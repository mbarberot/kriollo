package kriollo.generator.maven

import kriollo.configuration.JavaArtifact

data class PomModel(
    val mainClass: String,
    val dependencies: List<JavaArtifact>,
)