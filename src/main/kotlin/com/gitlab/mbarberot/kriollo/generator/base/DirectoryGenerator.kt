package com.gitlab.mbarberot.kriollo.generator.base

import com.gitlab.mbarberot.kriollo.generator.Generator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

abstract class DirectoryGenerator : Generator() {
    override fun execute(serviceProvider: ServiceProvider) {
        serviceProvider.fileSystem.createDirectory(getDirectoryPath())
    }

    abstract fun getDirectoryPath(): String
}