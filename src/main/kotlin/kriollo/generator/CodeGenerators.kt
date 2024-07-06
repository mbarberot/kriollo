package kriollo.generator

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.nix.NixGenerator
import kriollo.generator.templating.JteGenerator

class CodeGenerators {

    private val generators: List<Generator> = listOf(
        NixGenerator(),
        JteGenerator(),
    )

    fun execute(configuration: CodegenConfiguration) {
        generators
            .filter { it.isActivated(configuration)}
            .forEach { it.execute(configuration) }

    }
}