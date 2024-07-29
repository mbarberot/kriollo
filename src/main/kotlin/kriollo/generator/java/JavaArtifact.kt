package kriollo.generator.java

data class JavaArtifact(
    val groupId: String,
    val artifactId: String,
    val version: String? = null,
    val scope: String? = null
)