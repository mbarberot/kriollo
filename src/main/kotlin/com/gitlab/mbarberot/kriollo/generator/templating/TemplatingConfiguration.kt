package com.gitlab.mbarberot.kriollo.generator.templating

import com.gitlab.mbarberot.kriollo.generator.templating.jte.JteConfiguration

data class TemplatingConfiguration(
    val enabled: Boolean = false,
    val jte: JteConfiguration = JteConfiguration()
)