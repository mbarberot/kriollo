package kriollo.generator.components.kriollo

import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider

class KriolloExtensionsGenerator(
    val serviceProvider: ServiceProvider
) : Generator() {

    override fun execute( serviceProvider: ServiceProvider) {
        serviceProvider
            .configuration
            .components
            .kriollo
            .flatMap { moduleConfiguration ->
                moduleConfiguration
                    .provideExtensions
                    .map { extensionConfiguration ->
                        KriolloExtensionGenerator(moduleConfiguration, extensionConfiguration)
                    }
            }
            .forEach { generator -> generator.execute(serviceProvider) }
    }
}