package kriollo.generator.components.kriollo

import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider

class KriolloModulesGenerator(
    val serviceProvider: ServiceProvider
) : Generator() {

    override fun execute( serviceProvider: ServiceProvider) {
        serviceProvider.configuration
            .components
            .kriollo
            .map { moduleConfiguration -> KriolloModuleGenerator(moduleConfiguration) }
            .forEach { generator -> generator.execute(serviceProvider) }
    }
}