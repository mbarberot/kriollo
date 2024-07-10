package kriollo.generator.templating

import kriollo.generator.templating.jte.JteConfiguration

data class TemplatingConfiguration(
    val enabled: Boolean = false,
    val jte: JteConfiguration = JteConfiguration()
)