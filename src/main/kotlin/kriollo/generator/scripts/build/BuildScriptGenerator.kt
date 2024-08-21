package kriollo.generator.scripts.build

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.services.provider.ServiceProvider

class BuildScriptGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {

    override fun getFilePath(configuration: CodegenConfiguration) = "./${serviceProvider.configuration.scripts.dir}/build.sh"

    override fun isScript() = true

    override fun getTemplatePath(): String {
        return "generator/scripts/build.sh.kte"
    }

    override fun getTemplateData(): Any {
        return BuildScriptModel(
            serviceProvider.configuration.scripts.build.customSteps
        )
    }
}

