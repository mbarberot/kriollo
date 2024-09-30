package kriollo.generator.maven

import kriollo.generator.java.JavaArtifact

data class PomModel(
    val artifact: JavaArtifact,
    val dependencies: List<JavaArtifact>,
    val plugins: List<String>,
    val properties: Map<String, String>,
    val boms: List<JavaArtifact>,
    val repositories: PomRepositoriesModel,
)

data class PomRepositoriesModel(
    val jitpack: Boolean = false
) {
    fun isNotEmpty(): Boolean = listOf(jitpack).all { it }
}