package com.gitlab.mbarberot.kriollo.generator.nix

import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class BaseNixPackageExtension(val serviceProvider: ServiceProvider): DefaultNixFileExtension {
    override fun provide(): List<DefaultNixPartial> {
        return listOf(
            DefaultNixPartial(
                packages = listOf(
                    "jq"
                )
            )
        )
    }

}
