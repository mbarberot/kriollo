package kriollo.generator.scripts

import kriollo.generator.base.DirectoryGenerator
import kriollo.services.provider.ServiceProvider

class ScriptsDirectoryGenerator(val serviceProvider: ServiceProvider) : DirectoryGenerator() {
    override fun getDirectoryPath() = "./${serviceProvider.configuration.scripts.dir}"
}