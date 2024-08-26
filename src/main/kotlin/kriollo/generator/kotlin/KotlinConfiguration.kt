package kriollo.generator.kotlin

import kriollo.HardcodedValues

data class KotlinConfiguration(
    val enabled: Boolean = false,
    val version: String = HardcodedValues.DEFAULT_KOTLIN_VERSION,
)