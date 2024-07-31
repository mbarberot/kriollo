package kriollo.generator.scripts.build

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator

class BuildScriptGenerator(val configuration: CodegenConfiguration) : TemplatedFileGenerator() {

    override fun getFilePath(configuration: CodegenConfiguration) = "./${configuration.scripts.dir}/build.sh"

    override fun isScript() = true

    override fun getTemplatePath(): String {
        return "generator/scripts/build.sh.kte"
    }

    override fun getTemplateData(): Any {
        return BuildScriptModel(
            configuration.scripts.build.customSteps
        )
    }
}

