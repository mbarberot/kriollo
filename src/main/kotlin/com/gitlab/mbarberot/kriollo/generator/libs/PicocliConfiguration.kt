package com.gitlab.mbarberot.kriollo.generator.libs

import com.gitlab.mbarberot.kriollo.HardcodedValues

data class PicocliConfiguration(
    val enabled: Boolean = false,
    val version: String = HardcodedValues.DEFAULT_PICOCLI_VERSION,
)