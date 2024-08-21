package kriollo.generator.templating.jte

import kriollo.generator.base.DirectoryGenerator
import kriollo.services.provider.ServiceProvider

class JteDirectoryGenerator(val serviceProvider: ServiceProvider): DirectoryGenerator() {

    override fun getDirectoryPath(): String {
        return serviceProvider.configuration.templating.jte.sourceDirectory
    }
}