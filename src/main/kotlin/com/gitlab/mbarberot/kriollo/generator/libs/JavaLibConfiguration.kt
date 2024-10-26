package com.gitlab.mbarberot.kriollo.generator.libs

import com.gitlab.mbarberot.kriollo.generator.templating.jte.JteConfiguration

interface IJavaLibConfiguration{
    val jackson: JacksonConfiguration
    val picocli: PicocliConfiguration
    val feign: FeignConfiguration
    val jte: JteConfiguration
}

data class JavaLibConfiguration(
    override val jackson: JacksonConfiguration = JacksonConfiguration(),
    override val picocli: PicocliConfiguration = PicocliConfiguration(),
    override val feign: FeignConfiguration = FeignConfiguration(),
    override val jte: JteConfiguration = JteConfiguration(),
): IJavaLibConfiguration
