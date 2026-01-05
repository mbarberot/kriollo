package com.gitlab.mbarberot.kriollo.generator.nix

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.services.templating.JteTemplate
import com.gitlab.mbarberot.kriollo.services.templating.Template

class DefaultNixFileGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {

    private val extensions: MutableList<DefaultNixFileExtension> = mutableListOf()

    override fun getFilePath(): String = "default.nix"

    override fun getTemplate(): Template = JteTemplate(
        "generator/nix/default.nix.kte",
        DefaultNixFileModel(
            packages = extensions
                .flatMap { extensions -> extensions.provide() }
                .flatMap { partialNix -> partialNix.packages },
            shellHooks = extensions
                .flatMap { extensions -> extensions.provide() }
                .map { partialNix -> partialNix.shellHook }
                .filter { hook -> hook.isNotEmpty() },
        )
    )

    override fun registerExtension(extension: DefaultNixFileExtension) {
        this.extensions.add(extension)
    }
}
