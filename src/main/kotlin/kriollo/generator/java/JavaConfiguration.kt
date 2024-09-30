package kriollo.generator.java

import kriollo.HardcodedValues

data class JavaConfiguration(
    val enabled: Boolean = false,
    val version: String = HardcodedValues.DEFAULT_JAVA_VERSION,
)