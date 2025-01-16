package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.generator.git.GitIgnoreExtension
import com.gitlab.mbarberot.kriollo.generator.git.GitIgnoreRules
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class ProjectGitIgnoreExtension(val serviceProvider: ServiceProvider) : GitIgnoreExtension {

    override fun provide(): List<GitIgnoreRules> {
        return listOf(
            GitIgnoreRules(
                rules = listOf(".env"),
                description = "Project files"
            )
        )
    }

}
