package kriollo.generator.components.kriollo

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider

class KriolloModulesGenerator(
    val configuration: CodegenConfiguration,
) : Generator() {

    override fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) {
        configuration
            .components
            .kriollo
            .map { moduleConfiguration -> KriolloModuleGenerator(moduleConfiguration) }
            .forEach { generator -> generator.execute(configuration, serviceProvider) }
    }
}