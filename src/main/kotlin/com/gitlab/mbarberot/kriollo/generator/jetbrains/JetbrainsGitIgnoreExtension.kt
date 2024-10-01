package com.gitlab.mbarberot.kriollo.generator.jetbrains

import com.gitlab.mbarberot.kriollo.generator.git.GitIgnoreExtension
import com.gitlab.mbarberot.kriollo.generator.git.GitIgnoreRules
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

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
