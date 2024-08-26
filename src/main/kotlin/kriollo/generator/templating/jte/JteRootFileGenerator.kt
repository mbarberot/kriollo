package kriollo.generator.templating.jte

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.FileGenerator
import kriollo.services.provider.ServiceProvider

class JteRootFileGenerator(val serviceProvider: ServiceProvider): FileGenerator() {

    override fun getFilePath(): String {
        return "${serviceProvider.configuration.templating.jte.sourceDirectory}/.jteroot"
    }

    override fun getContent(serviceProvider: ServiceProvider) = ""
}