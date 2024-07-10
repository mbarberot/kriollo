package kriollo.generator.project

import kriollo.configuration.JavaArtifact

data class ProjectConfiguration(
    val mainClass: String,
    val dependencies: List<JavaArtifact> = listOf()
)