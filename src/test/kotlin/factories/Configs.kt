package factories

import kriollo.services.configuration.BasicCodegenConfiguration
import kriollo.configuration.CodegenConfiguration
import kriollo.generator.git.GitConfiguration
import kriollo.generator.nix.NixConfiguration
import kriollo.generator.project.ProjectConfiguration

object Configs {
    fun basicConfig(): CodegenConfiguration {
        return BasicCodegenConfiguration(
            project = ProjectConfiguration(name = "Acme")
        )
    }
}