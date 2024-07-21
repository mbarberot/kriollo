package kriollo.generator.project

import kriollo.generator.java.JavaArtifact

data class ProjectConfiguration(
    val mainClass: String,
    val encoding: String = "UTF-8",
    val dependencies: List<JavaArtifact> = listOf()
)