package kriollo.generator.git

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.generator.utils.ServiceProvider

class GitGenerator : Generator {

    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.git.enabled
    }

    override fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) {
        val gitignoreTemplate = """
            # /!\ Warning
            # This file is generated by Kriollo, do not edit it manually 
            
            # Intellij files
            .idea/
            
            # Java artifacts
            *.jar
            
            # Maven working directories
            target/
        """.trimIndent()

        serviceProvider.fileSystem.createFile(".gitignore", gitignoreTemplate)
    }

}