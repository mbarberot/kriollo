package kriollo.generator.components

import kriollo.generator.components.kriollo.configuration.KriolloComponentConfiguration

data class ComponentsConfiguration(
    val kriollo: List<KriolloComponentConfiguration> = listOf(),
)