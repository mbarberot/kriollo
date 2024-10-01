package com.gitlab.mbarberot.kriollo.generator.base

import com.gitlab.mbarberot.kriollo.generator.Generator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

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