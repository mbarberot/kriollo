package kriollo.generator.maven

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.nix.DefaultNixFileExtension
import kriollo.generator.nix.DefaultNixPartial
import kriollo.services.provider.ServiceProvider

class MavenDefaultNixExtension(
    val configuration: CodegenConfiguration,
    val serviceProvider: ServiceProvider
): DefaultNixFileExtension {
    override fun provide(): List<DefaultNixPartial> {
        return listOf(
            DefaultNixPartial(packages = listOf("maven"))
        )
    }
}
