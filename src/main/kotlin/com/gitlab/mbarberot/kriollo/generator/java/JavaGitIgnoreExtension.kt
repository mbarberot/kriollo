package com.gitlab.mbarberot.kriollo.generator.java

import com.gitlab.mbarberot.kriollo.generator.git.GitIgnoreExtension
import com.gitlab.mbarberot.kriollo.generator.git.GitIgnoreRules
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

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
