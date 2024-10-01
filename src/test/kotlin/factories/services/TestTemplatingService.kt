package factories.services

import com.gitlab.mbarberot.kriollo.services.templating.TemplatingService

class TestTemplatingService : TemplatingService {
    override fun renderToString(templatePath: String, templateData: Any): String {
        return "$templatePath-$templateData"
    }
}