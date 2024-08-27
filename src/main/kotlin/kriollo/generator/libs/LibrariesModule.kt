package kriollo.generator.libs

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import kriollo.generator.CodegenModule
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v2014.0.0")
class LibrariesModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = true

    override fun provideGenerators(): List<Generator> = listOf(
    )

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(JacksonJavaDependenciesExtension(serviceProvider))
    }
}