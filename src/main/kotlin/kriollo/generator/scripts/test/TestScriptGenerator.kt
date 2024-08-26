package kriollo.generator.scripts.test

import kriollo.generator.base.TemplatedFileGenerator
import kriollo.generator.utils.EmptyModel
import kriollo.services.provider.ServiceProvider

class TestScriptGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {

    override fun getFilePath() = "./${serviceProvider.configuration.scripts.dir}/test.sh"

    override fun isScript() = true

    override fun getTemplatePath(): String {
        return "generator/scripts/test.sh.kte"
    }

    override fun getTemplateData(): Any {
        return EmptyModel()
    }
}

