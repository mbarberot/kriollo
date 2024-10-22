package com.gitlab.mbarberot.kriollo.generator.cli.script

import com.gitlab.mbarberot.kriollo.generator.base.FileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class MainScriptFileGenerator(val serviceProvider: ServiceProvider) : FileGenerator() {

    override fun getFilePath(): String {
        val scriptConfiguration = serviceProvider.configuration.cli.script

        return "./${scriptConfiguration.targetDirectory}/${scriptConfiguration.fileName}"
    }

    override fun isScript(): Boolean = true

    override fun getContent(serviceProvider: ServiceProvider): String {
        val targetDirectory = serviceProvider.configuration.cli.script.targetDirectory
        val jarFilename = "${serviceProvider.configuration.project.name}.jar"
        return """
                #!/usr/bin/env bash
                
                # /!\ Warning
                # This file is generated by Kriollo, do not edit it manually 
                
                java -jar $targetDirectory/$jarFilename ${'$'}*
            """.trimIndent()
    }
}