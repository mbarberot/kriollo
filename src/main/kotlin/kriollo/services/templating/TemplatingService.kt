package kriollo.services.templating

interface TemplatingService {
    fun renderToString(templatePath: String, templateData: Any): String
}