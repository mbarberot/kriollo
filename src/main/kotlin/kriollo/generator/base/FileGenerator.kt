package kriollo.generator.base

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider

abstract class FileGenerator: Generator() {
    override fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) {
        serviceProvider.fileSystem.createFile(
            getFilePath(configuration),
            getContent(configuration, serviceProvider),
            isScript()
        )
    }

    abstract fun getFilePath(configuration: CodegenConfiguration): String
    abstract fun getContent(configuration: CodegenConfiguration, serviceProvider: ServiceProvider): String

    open fun isScript(): Boolean = false
}