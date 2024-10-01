package com.gitlab.mbarberot.kriollo.generator.libs

import com.gitlab.mbarberot.kriollo.generator.libs.jackson.JacksonConfiguration

data class JavaLibConfiguration(
    val jackson: JacksonConfiguration = JacksonConfiguration()
)