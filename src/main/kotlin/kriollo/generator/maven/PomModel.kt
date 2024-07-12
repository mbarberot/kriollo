package kriollo.generator.maven

import kriollo.configuration.JavaArtifact

data class PomModel(
    val dependencies: List<JavaArtifact>,
    val plugins: List<String>,
    val properties: Map<String, String>,
)