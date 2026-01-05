package com.gitlab.mbarberot.kriollo.services.templating

import com.github.jknack.handlebars.Handlebars
import com.gitlab.mbarberot.kriollo.KriolloException
import gg.jte.TemplateEngine
import gg.jte.output.StringOutput

class DefaultTemplatingService(
    private val jteTemplateEngine: TemplateEngine,
    private val handlebarsTemplateEngine: Handlebars
) : TemplatingService {

    override fun renderToString(template: Template): String {
        if(template.accept("jte")) {
            val output = StringOutput()
            jteTemplateEngine.render(template.templatePath, template.templateData, output)
            return output.toString()
        }

        if(template.accept("handlebars")) {
            val compiledTemplate = handlebarsTemplateEngine.compile(template.templatePath)
            return compiledTemplate.apply(template.templateData)
        }

        throw KriolloException("Template engine not supported")
    }

}