package com.gitlab.mbarberot.kriollo.generator.maven

import com.gitlab.mbarberot.kriollo.spi.ModuleFactory
import com.gitlab.mbarberot.kriollo.spi.ModuleProvider
import com.gitlab.mbarberot.kriollo.spi.configuration.ConfigurationCustomizer

@Suppress("unused") // Used by Service Loader
class MavenModuleProvider : ModuleProvider {

    override fun getConfigurationCustomizers(): List<ConfigurationCustomizer> {
        return listOf()
    }

    override fun getModuleFactory(): ModuleFactory {
        return ModuleFactory { serviceProvider ->
            if (serviceProvider.configurationService.get(MavenConfiguration::class).enabled) {
                MavenModule(serviceProvider)
            } else {
                null
            }
        }
    }
}