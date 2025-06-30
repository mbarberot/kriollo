package com.gitlab.mbarberot.kriollo.generator.nix

import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class CustomNixPackageExtension(val serviceProvider: ServiceProvider): DefaultNixFileExtension {
    override fun provide(): List<DefaultNixPartial> {
        return listOf(DefaultNixPartial(serviceProvider.configuration.nix.customPackages))
    }
}
