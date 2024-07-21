package kriollo.generator.maven

import kriollo.generator.java.JavaArtifact

data class PomModel(
    val dependencies: List<JavaArtifact>,
    val plugins: List<String>,
    val properties: Map<String, String>,
)