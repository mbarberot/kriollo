package kriollo.generator.java

import kriollo.generator.git.GitIgnoreExtension
import kriollo.generator.git.GitIgnoreRules
import kriollo.services.provider.ServiceProvider

class JavaGitIgnoreExtension(
    val serviceProvider: ServiceProvider
) : GitIgnoreExtension{
    override fun provide(): List<GitIgnoreRules> {
        return listOf(
            GitIgnoreRules(
                description = "Java",
                rules = listOf(
                    "*.jar",
                    "*.class"
                )
            )
        )
    }

}
