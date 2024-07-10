package kriollo.generator.libs

import kriollo.generator.libs.jackson.JacksonConfiguration

data class JavaLibConfiguration(
    val jackson: JacksonConfiguration = JacksonConfiguration()
)