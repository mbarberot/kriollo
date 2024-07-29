package kriollo.generator.maven

data class SimpleMavenPluginModel(
    val groupId: String? = null,
    val artifactId: String,
    val version: String? = null,
)