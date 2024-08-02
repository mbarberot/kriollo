package factories

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.KriolloConfiguration
import kriollo.generator.git.GitConfiguration
import kriollo.generator.nix.NixConfiguration

object Configs {
    fun basicConfig(): CodegenConfiguration {
        return CodegenConfiguration(
            nix = NixConfiguration(
                enabled = true
            ),
            git = GitConfiguration(
                enabled = true
            ),
        )
    }
}