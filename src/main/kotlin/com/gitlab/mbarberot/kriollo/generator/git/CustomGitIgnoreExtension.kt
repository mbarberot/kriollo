package com.gitlab.mbarberot.kriollo.generator.git

import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class CustomGitIgnoreExtension(
    serviceProvider: ServiceProvider,
    private val configuration: GitConfiguration = serviceProvider.configuration.git,
) : GitIgnoreExtension {

    override fun provide(): List<GitIgnoreRules> {
        if (configuration.ignore.isEmpty()) {
            return listOf()
        }

        return listOf(
            GitIgnoreRules(
                rules = configuration.ignore,
                description = "Custom rules defined in Kriollo configuration"
            )
        )
    }

}
