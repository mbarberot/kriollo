package com.gitlab.mbarberot.kriollo.generator.templating.jte

import com.gitlab.mbarberot.kriollo.HardcodedValues

data class JteConfiguration(
    val enabled: Boolean = false,
    val groupId: String = "gg.jte",
    val artifactId: String = "jte",
    val version: String = HardcodedValues.DEFAULT_JTE_VERSION,
    val sourceDirectory: String = "src/main/jte",
    val contentType: String = "Html",
)