package kriollo.generator.maven

import kriollo.generator.git.GitIgnoreExtension
import kriollo.generator.git.GitIgnoreRules
import kriollo.services.provider.ServiceProvider

class MavenGitIgnoreExtension(
    val serviceProvider: ServiceProvider
): GitIgnoreExtension {
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
