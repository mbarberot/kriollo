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
            packages = extensions
                .flatMap { extensions -> extensions.provide() }
                .flatMap { partialNix -> partialNix.packages },
            shellHooks = extensions
                .flatMap { extensions -> extensions.provide() }
                .map { partialNix -> partialNix.shellHook }
                .filter { hook -> hook.isNotEmpty() },
        )
    }

    override fun registerExtension(extension: DefaultNixFileExtension) {
        this.extensions.add(extension)
    }
}
