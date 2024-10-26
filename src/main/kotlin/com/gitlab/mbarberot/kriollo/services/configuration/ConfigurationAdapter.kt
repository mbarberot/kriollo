package com.gitlab.mbarberot.kriollo.services.configuration

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration

fun interface ConfigurationAdapter<T> {
    fun adapt(backwardConfig: T): CodegenConfiguration
}

class NoOpConfigurationAdapter: ConfigurationAdapter<CodegenConfiguration> {
    override fun adapt(backwardConfig: CodegenConfiguration): CodegenConfiguration = backwardConfig
}