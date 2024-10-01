package com.gitlab.mbarberot.kriollo.generator.nix

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class DefaultNixFileGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {

    private val extensions: MutableList<DefaultNixFileExtension> = mutableListOf()

    override fun getFilePath(): String = "default.nix"

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
