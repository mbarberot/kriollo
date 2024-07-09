package kriollo.generator.cli.script

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.FileGenerator
import kriollo.generator.utils.ServiceProvider

class MainScriptFileGenerator(val configuration: CodegenConfiguration) : FileGenerator() {

    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.cli.enabled && configuration.cli.script.enabled
    }

    override fun getFilePath(configuration: CodegenConfiguration): String {
        val scriptConfiguration = configuration.cli.script

        return "./${scriptConfiguration.targetDirectory}/${scriptConfiguration.fileName}"
    }

    override fun isScript(): Boolean = true

    override fun getContent(configuration: CodegenConfiguration, serviceProvider: ServiceProvider): String {
        return """
                #!/usr/bin/env bash
                
                # /!\ Warning
                # This file is generated by Kriollo, do not edit it manually 
                
                java -jar ${configuration.cli.script.jarLocation} ${'$'}*
            """.trimIndent()
    }
}