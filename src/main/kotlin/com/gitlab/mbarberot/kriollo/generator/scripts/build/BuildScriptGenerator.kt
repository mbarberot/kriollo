package com.gitlab.mbarberot.kriollo.generator.scripts.build

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.services.templating.JteTemplate

class BuildScriptGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {

    override fun getFilePath() = "./${serviceProvider.configuration.scripts.dir}/build.sh"

    override fun isScript() = true

    override fun getTemplate() = JteTemplate(
        "generator/scripts/build.sh.kte",
        BuildScriptModel(serviceProvider.configuration.scripts.build.customSteps)
    )

}

