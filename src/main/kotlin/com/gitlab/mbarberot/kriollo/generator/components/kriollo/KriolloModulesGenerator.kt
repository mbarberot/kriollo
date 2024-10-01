package com.gitlab.mbarberot.kriollo.generator.components.kriollo

import com.gitlab.mbarberot.kriollo.generator.Generator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class KriolloModulesGenerator(
    val serviceProvider: ServiceProvider
) : Generator() {

    override fun execute( serviceProvider: ServiceProvider) {
        serviceProvider.configuration
            .components
            .kriollo
            .map { moduleConfiguration -> KriolloModuleGenerator(serviceProvider, moduleConfiguration) }
            .forEach { generator -> generator.execute(serviceProvider) }
    }
}