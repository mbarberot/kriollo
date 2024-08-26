package kriollo.generator.base

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider

abstract class FileGenerator: Generator() {
    override fun execute(serviceProvider: ServiceProvider) {
        serviceProvider.fileSystem.createFile(
            getFilePath(),
            getContent(serviceProvider),
            isScript()
        )
    }

    abstract fun getFilePath(): String
    abstract fun getContent(serviceProvider: ServiceProvider): String

    open fun isScript(): Boolean = false
}