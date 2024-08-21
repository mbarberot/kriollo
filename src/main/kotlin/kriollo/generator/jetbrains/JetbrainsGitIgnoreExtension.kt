package kriollo.generator.jetbrains

import kriollo.generator.git.GitIgnoreExtension
import kriollo.generator.git.GitIgnoreRules
import kriollo.services.provider.ServiceProvider

class JetbrainsGitIgnoreExtension(
   val serviceProvider: ServiceProvider
) : GitIgnoreExtension{
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
