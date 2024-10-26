package com.gitlab.mbarberot.kriollo.services.configuration

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration

class ConfigurationProvider<T>(
    configurationReader: ConfigurationReader<T>,
    configurationAdapter: ConfigurationAdapter<T>,
): CodegenConfiguration by configurationAdapter.adapt(configurationReader.read())