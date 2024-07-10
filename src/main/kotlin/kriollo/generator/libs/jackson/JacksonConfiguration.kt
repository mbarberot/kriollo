package kriollo.generator.libs.jackson

data class JacksonConfiguration(
    val version: String = "",
    val dataformats: List<String> = listOf()
)