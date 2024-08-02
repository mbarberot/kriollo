package kriollo.generator.templating.jte

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.FileGenerator
import kriollo.services.provider.ServiceProvider

class JteRootFileGenerator(val configuration: CodegenConfiguration): FileGenerator() {

    override fun getFilePath(configuration: CodegenConfiguration): String {
        return "${configuration.templating.jte.sourceDirectory}/.jteroot"
    }

    override fun getContent(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) = ""
}