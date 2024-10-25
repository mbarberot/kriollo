package com.gitlab.mbarberot.kriollo.features

import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class Clean(
    private val serviceProvider: ServiceProvider,
) {
    fun clean() {
        val index: List<String> = serviceProvider.fileSystem.getFileContent("codegen/generated-files.txt")

        index.forEach { filePath ->
            serviceProvider.fileSystem.removeFile(filePath)
        }
    }
}