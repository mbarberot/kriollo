package com.gitlab.mbarberot.kriollo.generator.maven

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact

data class PomModel(
    val artifact: JavaArtifact,
    val dependencies: List<JavaArtifact>,
    val plugins: List<String>,
    val properties: Map<String, String>,
    val boms: List<JavaArtifact>,
    val repositories: PomRepositoriesModel,
    val sourceDirectory: String,
    val testSourceDirectory: String,
)

data class PomRepositoriesModel(
    val jitpack: Boolean = false
) {
    fun isNotEmpty(): Boolean = listOf(jitpack).all { it }
}