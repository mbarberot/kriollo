package com.gitlab.mbarberot.kriollo.spi

import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationCustomizer

interface ModuleProvider {
    fun getConfigurationCustomizers(): List<ConfigurationCustomizer>
    fun getModuleFactory(): ModuleFactory
}