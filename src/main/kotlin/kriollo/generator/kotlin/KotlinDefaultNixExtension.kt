package kriollo.generator.kotlin

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.nix.DefaultNixFileExtension
import kriollo.generator.nix.DefaultNixPartial
import kriollo.generator.utils.ServiceProvider

class KotlinDefaultNixExtension(
    val configuration: CodegenConfiguration,
    val serviceProvider: ServiceProvider
): DefaultNixFileExtension {
    override fun provide(): List<DefaultNixPartial> {
        return listOf(
            DefaultNixPartial(packages = listOf("kotlin"))
        )
    }
}
