package kriollo.generator.cli.script

import kriollo.generator.base.DirectoryGenerator
import kriollo.services.provider.ServiceProvider

class MainScriptDirectoryGenerator(val serviceProvider: ServiceProvider): DirectoryGenerator() {

    override fun getDirectoryPath(): String {
        return serviceProvider.configuration.cli.script.targetDirectory
    }
}