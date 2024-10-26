package com.gitlab.mbarberot.kriollo.generator.libs

data class JavaLibConfiguration(
    val jackson: JacksonConfiguration = JacksonConfiguration(),
    val picocli: PicocliConfiguration = PicocliConfiguration(),
    val feign: FeignConfiguration = FeignConfiguration(),
)
