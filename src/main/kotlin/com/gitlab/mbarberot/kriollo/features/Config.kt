package com.gitlab.mbarberot.kriollo.features

import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationAdapter
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationReader
import com.gitlab.mbarberot.kriollo.services.configuration.kdl.KdlConfigurationReader
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyCodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyConfigurationAdapter

class Config<T>(
    private val reader: ConfigurationReader<T>,
    private val adapter: ConfigurationAdapter<T>
) {
    fun check() {
        adapter.adapt(reader.read())
    }

    companion object { // TODO factory somewhere ?
        fun default(): Config<LegacyCodegenConfiguration> {
            return Config(
                KdlConfigurationReader("./codegen/codegen.kdl", LegacyCodegenConfiguration::class.java),
                LegacyConfigurationAdapter()
            )
        }
    }
}