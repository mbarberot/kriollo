package kriollo.generator.scripts.test

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.generator.utils.EmptyModel

class TestScriptGenerator(val configuration: CodegenConfiguration) : TemplatedFileGenerator() {

    override fun getFilePath(configuration: CodegenConfiguration) = "./${configuration.scripts.dir}/test.sh"

    override fun isScript() = true

    override fun getTemplatePath(): String {
        return "generator/scripts/test.sh.kte"
    }

    override fun getTemplateData(): Any {
        return EmptyModel()
    }
}

