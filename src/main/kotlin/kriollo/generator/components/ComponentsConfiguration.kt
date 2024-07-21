package kriollo.generator.components

import kriollo.generator.components.kriollo.KriolloComponentConfiguration

data class ComponentsConfiguration(
    val kriollo: List<KriolloComponentConfiguration> = listOf(),
)