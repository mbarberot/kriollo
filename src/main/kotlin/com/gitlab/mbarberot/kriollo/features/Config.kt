package com.gitlab.mbarberot.kriollo.features

import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationAdapter
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationReader
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationService
import com.gitlab.mbarberot.kriollo.services.configuration.kdl.KdlConfigurationReader
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyCodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyConfigurationAdapter

class Config<T>(
    private val configurationService: ConfigurationService,
    private val reader: ConfigurationReader<T>,
    private val adapter: ConfigurationAdapter<T>
) {
    fun check() {
        adapter.adapt(reader.read())
        configurationService.check()
    }

    companion object { // TODO factory somewhere ?
        fun default(configurationService: ConfigurationService): Config<LegacyCodegenConfiguration> {
            return Config(
                configurationService,
                KdlConfigurationReader("./codegen/codegen.kdl", LegacyCodegenConfiguration::class.java),
                LegacyConfigurationAdapter()
            )
        }
    }
}