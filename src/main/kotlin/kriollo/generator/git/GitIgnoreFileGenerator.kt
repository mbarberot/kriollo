package kriollo.generator.git

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.services.provider.ServiceProvider

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