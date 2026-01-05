package com.gitlab.mbarberot.kriollo.services.templating

interface TemplatingService {
    fun renderToString(templatePath: String, templateData: Any): String =
        renderToString(JteTemplate(templatePath, templateData))

    fun renderToString(template: Template): String
}

abstract class Template(
    val templatePath: String,
    val templateData: Any,
) {
    abstract fun accept(engine: String): Boolean
}

class JteTemplate(templatePath: String, templateData: Any) : Template(templatePath, templateData) {
    override fun accept(engine: String): Boolean {
        return engine == "jte"
    }
}

class HandlebarsTemplate(templatePath: String, templateData: Any) : Template(templatePath, templateData) {
    override fun accept(engine: String): Boolean {
        return engine == "handlebars"
    }
}

