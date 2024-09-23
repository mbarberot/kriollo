package factories

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.project.ProjectConfiguration
import kriollo.services.configuration.BasicCodegenConfiguration

object Configs {
    fun basicConfig(): CodegenConfiguration {
        return BasicCodegenConfiguration(
            project = ProjectConfiguration(name = "Acme")
        )
    }
}