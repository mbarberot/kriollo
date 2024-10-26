package com.gitlab.mbarberot.kriollo.generator.libs

import com.gitlab.mbarberot.kriollo.HardcodedValues

data class FeignConfiguration(
    val enabled: Boolean = false,
    val version: String = HardcodedValues.DEFAULT_FEIGN_VERSION
)
