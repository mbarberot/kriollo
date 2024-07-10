package kriollo.generator.maven

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.extensions.GitIgnoreExtension
import kriollo.generator.git.GitIgnoreRules
import kriollo.generator.utils.ServiceProvider

class MavenGitIgnoreExtension(configuration: CodegenConfiguration, serviceProvider: ServiceProvider): GitIgnoreExtension {
    override fun provide(): List<GitIgnoreRules> {
        return listOf(
            GitIgnoreRules(
                description = "Maven",
                rules = listOf(
                    "target/"
                )
            )
        )
    }
}
