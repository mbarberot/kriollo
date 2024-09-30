package kriollo.generator.scripts.build

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import kriollo.generator.CodegenModule
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v2024.2.0")
class BuildScriptModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = serviceProvider.configuration.scripts.build.enabled

    override fun provideGenerators(): List<Generator> = listOf(
        BuildScriptGenerator(serviceProvider),
    )

}