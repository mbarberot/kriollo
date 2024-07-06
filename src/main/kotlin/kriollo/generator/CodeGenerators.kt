package kriollo.generator

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.git.GitGenerator
import kriollo.generator.maven.MavenGenerator
import kriollo.generator.nix.NixGenerator
import kriollo.generator.script.BuildScriptGenerator
import kriollo.generator.templating.JteGenerator

class CodeGenerators {

    private val generators: List<Generator> = listOf(
        NixGenerator(),
        JteGenerator(),
        GitGenerator(),
        BuildScriptGenerator(),
        MavenGenerator(),
    )

    fun execute(configuration: CodegenConfiguration) {
        generators
            .filter { it.isActivated(configuration)}
            .forEach { it.execute(configuration) }

    }
}