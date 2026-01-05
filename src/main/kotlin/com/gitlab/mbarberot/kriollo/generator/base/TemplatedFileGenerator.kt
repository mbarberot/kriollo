package com.gitlab.mbarberot.kriollo.generator.base

import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.services.templating.Template

abstract class TemplatedFileGenerator : FileGenerator() {

    override fun getContent(serviceProvider: ServiceProvider): String {
        return serviceProvider.templatingService.renderToString(getTemplate())
    }

    abstract fun getTemplate(): Template
}