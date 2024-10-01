package com.gitlab.mbarberot.kriollo.services.templating

import gg.jte.TemplateEngine
import gg.jte.output.StringOutput

class DefaultTemplatingService(
    private val templateEngine: TemplateEngine,
) : TemplatingService {

    override fun renderToString(templatePath: String, templateData: Any): String {
        val output = StringOutput()
        templateEngine.render(templatePath, templateData, output)
        return output.toString()
    }

}