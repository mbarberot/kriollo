package kriollo.generator.maven

import kriollo.configuration.JavaArtifact

data class PomModel(
    val mainClass: String,
    val dependencies: List<JavaArtifact>,
    val jte: JteModel
)

data class JteModel(
    val version: String,
    val sourceDirectory: String,
    val contentType: String,
)