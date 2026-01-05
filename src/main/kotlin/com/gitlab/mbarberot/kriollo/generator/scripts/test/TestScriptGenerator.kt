package com.gitlab.mbarberot.kriollo.generator.scripts.test

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.generator.utils.EmptyModel
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.services.templating.JteTemplate
import com.gitlab.mbarberot.kriollo.services.templating.Template

class TestScriptGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {

    override fun getFilePath() = "./${serviceProvider.configuration.scripts.dir}/test.sh"

    override fun isScript() = true

    override fun getTemplate(): Template {
        return JteTemplate(
            "generator/scripts/test.sh.kte",
            EmptyModel()
        )
    }

}

