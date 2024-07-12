package kriollo.generator.components.kriollo

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.generator.utils.ServiceProvider

class KriolloExtensionsGenerator(
    val configuration: CodegenConfiguration,
) : Generator() {

    override fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) {
        configuration
            .components
            .kriollo
            .flatMap { moduleConfiguration ->
                moduleConfiguration
                    .provideExtensions
                    .map { extensionConfiguration ->
                        KriolloExtensionGenerator(moduleConfiguration, extensionConfiguration)
                    }
            }
            .forEach { generator -> generator.execute(configuration, serviceProvider) }
    }
}