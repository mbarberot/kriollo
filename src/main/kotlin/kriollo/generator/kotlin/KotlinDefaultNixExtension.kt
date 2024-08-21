package kriollo.generator.kotlin

import kriollo.generator.nix.DefaultNixFileExtension
import kriollo.generator.nix.DefaultNixPartial
import kriollo.services.provider.ServiceProvider

class KotlinDefaultNixExtension(
    val serviceProvider: ServiceProvider
): DefaultNixFileExtension {
    override fun provide(): List<DefaultNixPartial> {
        return listOf(
            DefaultNixPartial(packages = listOf("kotlin"))
        )
    }
}
