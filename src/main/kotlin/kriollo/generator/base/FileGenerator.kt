package kriollo.generator.base

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.generator.utils.ServiceProvider

abstract class FileGenerator(val isScript: Boolean = false): Generator() {
    override fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) {
        serviceProvider.fileSystem.createFile(
            getFilePath(configuration),
            getContent(configuration, serviceProvider),
            isScript
        )
    }

    abstract fun getFilePath(configuration: CodegenConfiguration): String
    abstract fun getContent(configuration: CodegenConfiguration, serviceProvider: ServiceProvider): String
}