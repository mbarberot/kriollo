package factories

import kriollo.services.configuration.BasicCodegenConfiguration
import kriollo.configuration.CodegenConfiguration
import kriollo.generator.git.GitConfiguration
import kriollo.generator.nix.NixConfiguration

object Configs {
    fun basicConfig(): CodegenConfiguration {
        return BasicCodegenConfiguration(
            nix = NixConfiguration(
                enabled = true
            ),
            git = GitConfiguration(
                enabled = true
            ),
        )
    }
}