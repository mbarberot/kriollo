package com.gitlab.mbarberot.kriollo.generator.kotlin

import com.gitlab.mbarberot.kriollo.HardcodedValues
import com.gitlab.mbarberot.kriollo.generator.components.kriollo.KriolloModule
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationCustomizer
import com.gitlab.mbarberot.kriollo.services.configuration.RootNode
import com.gitlab.mbarberot.kriollo.spi.ModuleFactory
import com.gitlab.mbarberot.kriollo.spi.ModuleProvider
import kotlin.reflect.KClass

@Suppress("unused") // Used by Service Loader
class KotlinModuleProvider: ModuleProvider {

    override fun getConfigurationCustomizers(): List<ConfigurationCustomizer> {
        return listOf(
            object : ConfigurationCustomizer {
                override val priority = 1

                override fun customize(configuration: RootNode) {
                    if(configuration.get("$.kotlin.version") == null) {
                        configuration.get("$.kotlin")?.setValue("version", HardcodedValues.DEFAULT_KOTLIN_VERSION)
                    }
                }

                override fun <T : Any> accepts(kclass: KClass<T>): Boolean {
                    return kclass == KotlinConfiguration::class
                }
            }
        )
    }

    override fun getModuleFactory(): ModuleFactory {
        return ModuleFactory { serviceProvider ->
            if (serviceProvider.configurationService.get(KotlinConfiguration::class).enabled) {
                KriolloModule(serviceProvider)
            } else {
                null
            }
        }
    }
}


