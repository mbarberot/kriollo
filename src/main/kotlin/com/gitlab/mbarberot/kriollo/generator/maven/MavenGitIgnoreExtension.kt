package com.gitlab.mbarberot.kriollo.generator.maven

import com.gitlab.mbarberot.kriollo.generator.git.GitIgnoreExtension
import com.gitlab.mbarberot.kriollo.generator.git.GitIgnoreRules
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

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
