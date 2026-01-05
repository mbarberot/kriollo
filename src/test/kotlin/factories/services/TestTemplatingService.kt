package factories.services

import com.gitlab.mbarberot.kriollo.services.templating.Template
import com.gitlab.mbarberot.kriollo.services.templating.TemplatingService

class TestTemplatingService : TemplatingService {
    override fun renderToString(template: Template): String {
        return "${template.templatePath}-${template.templateData}"
    }
}