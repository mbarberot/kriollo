package kriollo.generator.templating

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.generator.utils.createDirectories
import kriollo.generator.utils.initFile


class JteGenerator: Generator {

    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.templating.jte.enabled
    }

    override fun execute(configuration: CodegenConfiguration) {
        createDirectories(configuration.templating.jte.sourceDirectory)
        initFile("${configuration.templating.jte.sourceDirectory}/.jteroot", "")
    }
}