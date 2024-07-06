package kriollo.generator.utils

import gg.jte.TemplateEngine
import gg.jte.output.StringOutput

class TemplatingService(
    val templateEngine: TemplateEngine
) {

    fun renderToString(templatePath: String, templateData: Any): String {
        val output = StringOutput()
        templateEngine.render(templatePath, templateData, output)
        return output.toString()
    }

}