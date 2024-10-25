package com.gitlab.mbarberot.kriollo.features

import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class Clean(
    private val serviceProvider: ServiceProvider,
) {
    fun clean() {
        println("# Clean #")

        val indexFileName = "${serviceProvider.configuration.kriollo.dir}/generated-files.txt"
        val index: List<String> = serviceProvider.fileSystem.getFileContent(indexFileName)

        index.forEach { filePath ->
            serviceProvider.fileSystem.removeFile(filePath)
        }
    }
}