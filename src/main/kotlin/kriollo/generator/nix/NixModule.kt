package kriollo.generator.nix

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import kriollo.generator.CodegenModule
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v2024.2.0")
class NixModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = serviceProvider.configuration.nix.enabled

    override fun provideGenerators(): List<Generator> = listOf(
        DefaultNixFileGenerator(serviceProvider),
    )

}