package kriollo.generator.maven

import kriollo.generator.nix.DefaultNixFileExtension
import kriollo.generator.nix.DefaultNixPartial
import kriollo.services.provider.ServiceProvider

class MavenDefaultNixExtension(
    val serviceProvider: ServiceProvider,
) : DefaultNixFileExtension {
    override fun provide(): List<DefaultNixPartial> {
        return listOf(
            DefaultNixPartial(packages = listOf("maven"))
        )
    }
}
