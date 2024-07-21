package kriollo.generator.java

data class JavaArtifact(
    val groupId: String,
    val artifactId: String,
    val version: String,
    val scope: String? = null
)