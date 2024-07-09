package kriollo.generator.maven

import kriollo.configuration.JavaArtifact

data class PomModel(
    val mainClass: String,
    val dependencies: List<JavaArtifact>,
    val plugins: List<String>,
    val jte: JteModel,
    val kotlin: KotlinModel,
)

data class JteModel(
    val version: String,
    val sourceDirectory: String,
    val contentType: String,
)

data class KotlinModel(
    val version: String,
)