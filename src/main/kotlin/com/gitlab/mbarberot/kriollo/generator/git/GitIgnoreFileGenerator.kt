package com.gitlab.mbarberot.kriollo.generator.git

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.services.templating.HandlebarsTemplate
import com.gitlab.mbarberot.kriollo.services.templating.JteTemplate

class GitIgnoreFileGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {

    private val extensions: MutableList<GitIgnoreExtension> = mutableListOf()

    override fun getFilePath() = ".gitignore"

    override fun getTemplate() = HandlebarsTemplate(
        "generator/git/gitignore",
        GitIgnoreModel(
            blocks = buildList {
                extensions
                    .map { extension -> extension.provide() }
                    .forEach { rules -> addAll(rules) }
            }
        )
    )

    override fun registerExtension(extension: GitIgnoreExtension) {
        extensions.add(extension)
    }
}