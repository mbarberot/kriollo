package com.gitlab.mbarberot.kriollo.generator.scripts.build

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class BuildScriptGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {

    override fun getFilePath() = "./${serviceProvider.configuration.scripts.dir}/build.sh"

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

