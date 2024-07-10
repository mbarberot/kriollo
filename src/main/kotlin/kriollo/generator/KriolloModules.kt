package kriollo.generator

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.components.kriollo.KriolloModule
import kriollo.generator.utils.ServiceProvider

class KriolloModules {
    fun getModules(configuration: CodegenConfiguration, serviceProvider: ServiceProvider): List<CodegenModule> {
        return listOf(
            KriolloModule(configuration, serviceProvider),
        )
    }
}