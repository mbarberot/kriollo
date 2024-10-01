package com.gitlab.mbarberot.kriollo.generator.scripts.test

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.generator.utils.EmptyModel
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

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

