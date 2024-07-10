package kriollo.generator.templating.jte

data class JteConfiguration(
    val enabled: Boolean = false,
    val groupId: String = "gg.jte",
    val artifactId: String = "jte",
    val version: String = "",
    val sourceDirectory: String = "src/main/jte",
    val contentType: String = "Html",
)