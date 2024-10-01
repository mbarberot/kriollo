package com.gitlab.mbarberot.kriollo.generator.maven

import com.gitlab.mbarberot.kriollo.generator.nix.DefaultNixFileExtension
import com.gitlab.mbarberot.kriollo.generator.nix.DefaultNixPartial
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class MavenDefaultNixExtension(
    val serviceProvider: ServiceProvider,
) : DefaultNixFileExtension {
    override fun provide(): List<DefaultNixPartial> {
        return listOf(
            DefaultNixPartial(packages = listOf("maven"))
        )
    }
}
