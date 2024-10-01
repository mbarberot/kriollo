package com.gitlab.mbarberot.kriollo.generator.libs.jackson

import com.gitlab.mbarberot.kriollo.HardcodedValues

data class JacksonConfiguration(
    val version: String = HardcodedValues.DEFAULT_JACKSON_VERSION,
    val core: List<String> = listOf()
)