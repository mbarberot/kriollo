package kriollo.generator.project

import kriollo.generator.java.JavaArtifact

data class ProjectConfiguration(
    val mainClass: String,
    val dependencies: List<JavaArtifact> = listOf()
)