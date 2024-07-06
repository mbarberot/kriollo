package kriollo.generator.templating

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.utils.createDirectories
import kriollo.generator.utils.initFile

fun initJte(codegenConfiguration: CodegenConfiguration) {
    createDirectories(codegenConfiguration.templating.jte.sourceDirectory)
    initFile("${codegenConfiguration.templating.jte.sourceDirectory}/.jteroot", "")
}