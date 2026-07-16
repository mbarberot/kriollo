package com.gitlab.mbarberot.kriollo.generator.kotlin

import com.gitlab.mbarberot.kriollo.HardcodedValues
import com.gitlab.mbarberot.kriollo.generator.components.kriollo.KriolloModule
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.DeprecationHandler
import com.gitlab.mbarberot.kriollo.spi.ModuleFactory
import com.gitlab.mbarberot.kriollo.spi.ModuleProvider
import com.gitlab.mbarberot.kriollo.spi.configuration.ConfigurationCustomizer
import com.gitlab.mbarberot.kriollo.spi.configuration.RootNode
import com.gitlab.mbarberot.kriollo.utils.SemVer
import kotlin.reflect.KClass

@Suppress("unused") // Used by Service Loader
class KotlinModuleProvider : ModuleProvider {

    override fun getConfigurationCustomizers(): List<ConfigurationCustomizer> {
        return listOf(
            object : ConfigurationCustomizer {
                override val priority = 1

                override fun customize(configuration: RootNode) {
                    if (configuration.get("$.kotlin.version") == null) {
                        val version = configuration.get("$.kriollo.version")?.value()
                        val kotlinVersion = if (version != null && SemVer(version).isNewerOrEqualTo("2025.2.0")) {
                            HardcodedValues.DEFAULT_KOTLIN_VERSION
                        } else {
                            HardcodedValues.LEGACY_DEFAULT_KOTLIN_VERSION
                        }
                        configuration.get("$.kotlin")?.setValue("version", kotlinVersion)
                    }
                }

                override fun check(configuration: RootNode, deprecationHandler: DeprecationHandler) {
                    val kotlinVersionInConfig = configuration.get("$.kotlin.version")?.value()
                    if (kotlinVersionInConfig != null) {
                        val kotlinVersionRecommended = HardcodedValues.DEFAULT_KOTLIN_VERSION
                        if (SemVer(kotlinVersionRecommended).isEqualTo(kotlinVersionInConfig)) {
                            deprecationHandler.logDeprecation("kotlin.version is up to date with Kriollo's recommended version and can be discarded")
                        }
                        if (SemVer(kotlinVersionRecommended).isNewerTo(kotlinVersionInConfig)) {
                            deprecationHandler.logDeprecation(
                                "kotlin.version is outdated, please consider discarding this configuration to use the Kriollo's recommended version"
                            )
                        }
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
                KotlinModule(serviceProvider)
            } else {
                null
            }
        }
    }
}


