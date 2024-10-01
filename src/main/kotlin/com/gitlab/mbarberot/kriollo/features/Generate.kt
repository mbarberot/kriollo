package com.gitlab.mbarberot.kriollo.features

import com.gitlab.mbarberot.kriollo.generator.CodeGenerators
import com.gitlab.mbarberot.kriollo.generator.CoreModules
import com.gitlab.mbarberot.kriollo.generator.KriolloModules
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class Generate(
    private val serviceProvider: ServiceProvider,
) {

    private val generators: com.gitlab.mbarberot.kriollo.generator.CodeGenerators by lazy {
        com.gitlab.mbarberot.kriollo.generator.CodeGenerators(
            buildList {
                addAll(CoreModules().getModules(serviceProvider))
                addAll(KriolloModules().getModules(serviceProvider))
            },
        )
    }

    fun generate() {
        generators.execute(serviceProvider);
        createIndexFile()
    }

    private fun createIndexFile() {
        val fileSystem = serviceProvider.fileSystem

        fileSystem.createFile(
            filename = "${serviceProvider.configuration.kriollo.dir}/generated-files.txt",
            content = fileSystem.generatedFiles().joinToString("\n") { it.filename }
        )
    }
}