package com.gitlab.mbarberot.kriollo.generator.git

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class GitIgnoreFileGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {

    private val extensions: MutableList<GitIgnoreExtension> = mutableListOf()

    override fun getFilePath() = ".gitignore"

    override fun getTemplatePath(): String = "generator/git/gitignore.kte"

    override fun getTemplateData(): Any {
        return GitIgnoreModel(
            blocks = buildList {
                extensions
                    .map { extension -> extension.provide() }
                    .forEach { rules -> addAll(rules) }
            }
        )
    }

    override fun registerExtension(extension: GitIgnoreExtension) {
        extensions.add(extension)
    }
}