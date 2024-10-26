package com.gitlab.mbarberot.kriollo.generator.libs

import com.gitlab.mbarberot.kriollo.HardcodedValues

data class JacksonConfiguration(
    val enabled: Boolean = false,
    val version: String = HardcodedValues.DEFAULT_JACKSON_VERSION,
    val core: List<String> = listOf()
)