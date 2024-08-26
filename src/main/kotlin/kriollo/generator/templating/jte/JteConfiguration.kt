package kriollo.generator.templating.jte

import kriollo.HardcodedValues

data class JteConfiguration(
    val enabled: Boolean = false,
    val groupId: String = "gg.jte",
    val artifactId: String = "jte",
    val version: String = HardcodedValues.DEFAULT_JTE_VERSION,
    val sourceDirectory: String = "src/main/jte",
    val contentType: String = "Html",
)