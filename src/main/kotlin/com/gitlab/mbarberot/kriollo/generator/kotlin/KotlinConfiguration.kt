package com.gitlab.mbarberot.kriollo.generator.kotlin

import com.gitlab.mbarberot.kriollo.HardcodedValues

data class KotlinConfiguration(
    val enabled: Boolean = false,
    val version: String = HardcodedValues.DEFAULT_KOTLIN_VERSION,
)