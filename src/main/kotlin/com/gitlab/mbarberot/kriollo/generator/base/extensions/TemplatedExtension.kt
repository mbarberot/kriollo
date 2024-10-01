package com.gitlab.mbarberot.kriollo.generator.base.extensions

import com.gitlab.mbarberot.kriollo.generator.CodegenExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

abstract class TemplatedExtension(val serviceProvider: ServiceProvider): CodegenExtension<String> {
    abstract fun getTemplatePath(): String
    abstract fun getAllTemplateData(): List<Any>

    override fun provide(): List<String> {
        return getAllTemplateData()
            .map { model -> render(model) }
    }

    private fun render(data: Any): String {
        return serviceProvider.templatingService.renderToString(getTemplatePath(), data)
    }
}