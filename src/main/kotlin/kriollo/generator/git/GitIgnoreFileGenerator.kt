package kriollo.generator.git

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.generator.base.extensions.GitIgnoreExtension

class GitIgnoreFileGenerator(val configuration: CodegenConfiguration) : TemplatedFileGenerator() {

    private val extensions: MutableList<GitIgnoreExtension> = mutableListOf()

    override fun getFilePath(configuration: CodegenConfiguration) = ".gitignore"

    override fun getTemplatePath(): String = "generator/git/gitignore.kte"

    override fun getTemplateData(): Any {
        return GitIgnoreModel(
            blocks = buildList {
                extensions
                    .map { extension -> extension.provide() }
                    .forEach { rules -> addAll(rules) }
            }
//            listOf(
//                GitIgnoreBlockModel(
//                    "Intellij files",
//                    listOf("/.idea/", "*.iml")
//                ),
//                GitIgnoreBlockModel(
//                    "Java artifacts",
//                    listOf("*.jar")
//                ),
//                GitIgnoreBlockModel(
//                    "Maven working directories",
//                    listOf("target/")
//                )
//            )
        )
    }

    override fun registerExtension(extension: GitIgnoreExtension) {
        extensions.add(extension)
    }
}