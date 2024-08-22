package kriollo.generator.cli.script

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import kriollo.generator.CodegenModule
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v2014.0.0-SNAPSHOT")
class MainScriptModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = serviceProvider.configuration.cli.script.enabled

    override fun provideGenerators(): List<Generator> = listOf(
        MainScriptDirectoryGenerator(serviceProvider),
        MainScriptFileGenerator(serviceProvider),
    )

}