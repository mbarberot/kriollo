package com.gitlab.mbarberot.kriollo.generator.base

import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

abstract class TemplatedFileGenerator : FileGenerator() {

    override fun getContent(serviceProvider: ServiceProvider): String {
        return serviceProvider.templatingService.renderToString(
            getTemplatePath(),
            getTemplateData()
        )
    }

    abstract fun getTemplatePath(): String
    abstract fun getTemplateData(): Any

}