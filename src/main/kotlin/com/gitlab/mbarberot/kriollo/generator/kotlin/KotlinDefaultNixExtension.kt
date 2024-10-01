package com.gitlab.mbarberot.kriollo.generator.kotlin

import com.gitlab.mbarberot.kriollo.generator.nix.DefaultNixFileExtension
import com.gitlab.mbarberot.kriollo.generator.nix.DefaultNixPartial
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class KotlinDefaultNixExtension(
    val serviceProvider: ServiceProvider
): DefaultNixFileExtension {
    override fun provide(): List<DefaultNixPartial> {
        return listOf(
            DefaultNixPartial(packages = listOf("kotlin"))
        )
    }
}
