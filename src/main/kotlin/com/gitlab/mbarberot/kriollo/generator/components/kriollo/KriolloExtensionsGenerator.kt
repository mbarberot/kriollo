package com.gitlab.mbarberot.kriollo.generator.components.kriollo

import com.gitlab.mbarberot.kriollo.generator.Generator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

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
                        KriolloExtensionGenerator(serviceProvider, moduleConfiguration, extensionConfiguration)
                    }
            }
            .forEach { generator -> generator.execute(serviceProvider) }
    }
}