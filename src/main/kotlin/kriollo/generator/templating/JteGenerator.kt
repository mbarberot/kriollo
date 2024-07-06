package kriollo.generator.templating

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.generator.utils.ServiceProvider

class JteGenerator: Generator {

    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.templating.jte.enabled
    }

    override fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) {
        serviceProvider.fileSystem.createDirectory(configuration.templating.jte.sourceDirectory)
        serviceProvider.fileSystem.createFile("${configuration.templating.jte.sourceDirectory}/.jteroot")
    }
}