package com.gitlab.mbarberot.kriollo.generator.components

import com.gitlab.mbarberot.kriollo.generator.components.kriollo.KriolloComponentConfiguration

data class ComponentsConfiguration(
    val kriollo: List<KriolloComponentConfiguration> = listOf(),
)