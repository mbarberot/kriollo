package kriollo.generator.project

import kriollo.configuration.JavaArtifact

data class ProjectConfiguration(
    val mainClass: String, // TODO add artifact informations and finalName
    val dependencies: List<JavaArtifact> = listOf()
)