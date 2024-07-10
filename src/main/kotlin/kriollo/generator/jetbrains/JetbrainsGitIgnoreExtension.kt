package kriollo.generator.jetbrains

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.extensions.GitIgnoreExtension
import kriollo.generator.git.GitIgnoreRules
import kriollo.generator.utils.ServiceProvider

class JetbrainsGitIgnoreExtension(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) : GitIgnoreExtension{
    override fun provide(): List<GitIgnoreRules> {
        return listOf(
            GitIgnoreRules(
                description = "Jetbrains/Intellij files",
                rules = listOf(
                    "/.idea",
                    "*.iml"
                )
            )
        )
    }

}
