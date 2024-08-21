package kriollo.generator.base

import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider

abstract class DirectoryGenerator : Generator() {
    override fun execute(serviceProvider: ServiceProvider) {
        serviceProvider.fileSystem.createDirectory(getDirectoryPath())
    }

    abstract fun getDirectoryPath(): String
}