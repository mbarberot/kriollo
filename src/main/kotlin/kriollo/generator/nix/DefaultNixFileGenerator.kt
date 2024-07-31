package kriollo.generator.nix

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator

class DefaultNixFileGenerator(val configuration: CodegenConfiguration) : TemplatedFileGenerator() {

    private val extensions: MutableList<DefaultNixFileExtension> = mutableListOf()

    override fun getFilePath(configuration: CodegenConfiguration): String = "default.nix"

    override fun getTemplatePath(): String {
        return "generator/nix/default.nix.kte"
    }

    override fun getTemplateData(): Any {
        return DefaultNixFileModel(
            packages = buildList{
                addAll(listOf("jdk21", "maven", "kotlin"))
                extensions
                    .flatMap { extensions -> extensions.provide() }
                    .forEach { partialNix -> addAll(partialNix.packages) }
            },
            shellHooks = buildList {
                add("export JAVA_HOME=${'$'}{jdk21.home}")
                extensions
                    .flatMap { extensions -> extensions.provide() }
                    .forEach { partialNix -> add(partialNix.shellHook) }
            },
        )
    }

    override fun registerExtension(extension: DefaultNixFileExtension) {
        this.extensions.add(extension)
    }
}
