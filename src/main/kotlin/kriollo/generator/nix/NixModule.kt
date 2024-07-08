package kriollo.generator.nix

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.CodegenModule

class NixModule(configuration: CodegenConfiguration) : CodegenModule(configuration) {
    override fun isActivated(): Boolean = configuration.nix.enabled

    override fun provideGenerators() = listOf(
        DefaultNixFileGenerator(configuration),
    )
}