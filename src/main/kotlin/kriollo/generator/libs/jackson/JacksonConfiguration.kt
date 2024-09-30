package kriollo.generator.libs.jackson

import kriollo.HardcodedValues

data class JacksonConfiguration(
    val version: String = HardcodedValues.DEFAULT_JACKSON_VERSION,
    val core: List<String> = listOf()
)