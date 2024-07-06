package kriollo.generator

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.utils.ServiceProvider

interface Generator {
    fun isActivated(configuration: CodegenConfiguration): Boolean
    fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider)
}