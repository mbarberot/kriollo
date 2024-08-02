package factories.services

import kriollo.services.templating.TemplatingService

class TestTemplatingService : TemplatingService {
    override fun renderToString(templatePath: String, templateData: Any): String {
        return "$templatePath-$templateData"
    }
}