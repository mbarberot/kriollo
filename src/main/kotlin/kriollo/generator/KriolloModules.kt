package kriollo.generator

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import kriollo.generator.components.kriollo.KriolloModule
import kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v0.1.0")
class KriolloModules {
    fun getModules(serviceProvider: ServiceProvider): List<CodegenModule> {
        return listOf(
            KriolloModule(serviceProvider),
        )
    }
}