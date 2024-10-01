package com.gitlab.mbarberot.kriollo.generator.java

import com.gitlab.mbarberot.kriollo.HardcodedValues

data class JavaConfiguration(
    val enabled: Boolean = false,
    val version: String = HardcodedValues.DEFAULT_JAVA_VERSION,
)