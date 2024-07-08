package kriollo.generator.base

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.utils.ServiceProvider

abstract class TemplatedFileGenerator : FileGenerator() {

    override fun getContent(configuration: CodegenConfiguration, serviceProvider: ServiceProvider): String {
        return serviceProvider.templatingService.renderToString(
            getTemplatePath(),
            getTemplateData()
        )
    }

    abstract fun getTemplatePath(): String
    abstract fun getTemplateData(): Any

}