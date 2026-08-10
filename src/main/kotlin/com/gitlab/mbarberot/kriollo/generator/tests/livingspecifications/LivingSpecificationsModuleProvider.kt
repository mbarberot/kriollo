package com.gitlab.mbarberot.kriollo.generator.tests.livingspecifications

import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenModule
import com.gitlab.mbarberot.kriollo.generator.tests.TestsConfiguration
import com.gitlab.mbarberot.kriollo.spi.ModuleFactory
import com.gitlab.mbarberot.kriollo.spi.ModuleProvider
import com.gitlab.mbarberot.kriollo.spi.configuration.ConfigurationCustomizer

@Suppress("unused") // Used by Service Loader
class LivingSpecificationsModuleProvider : ModuleProvider {

    override fun getConfigurationCustomizers(): List<ConfigurationCustomizer> {
        return listOf()
    }

    override fun getModuleFactory(): ModuleFactory {
        return ModuleFactory { serviceProvider ->
            val testConfiguration = serviceProvider.configurationService.get(TestsConfiguration::class)
            if (testConfiguration.enabled && testConfiguration.livingSpecifications.enabled) {
                LivingSpecificationsModule(serviceProvider)
            } else {
                null
            }
        }
    }
}